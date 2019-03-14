package ru.job4j.filemanager;

import com.google.common.base.Joiner;
import org.junit.Test;
import java.io.*;
import java.net.Socket;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    private static final String LN = System.getProperty("line.separator");
    String firstPath = Joiner.on(LN).join("1", "./src/main/resources/fileManager", "");
    String firstAnswer =  Joiner.on(LN).join("Вы выбрали команду номер 1. Список каталога.",
            "Список файлов:",
            "Папка 1",
            ".DS_Store",
            "3.png",
            "",
            "");

    @Test
    public void whenYouChooseFirstOption() throws IOException {
        var socket = mock(Socket.class);
        var out = new ByteArrayOutputStream();
        var in = new ByteArrayInputStream((firstPath + "exit").getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        var server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(firstAnswer));
    }

    @Test
    public void whenYouChooseSecondOptionWithoutFirstOption() throws IOException {
        var socket = mock(Socket.class);
        var out = new ByteArrayOutputStream();
        var in = new ByteArrayInputStream(Joiner.on(LN).join("2", "exit").getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        var server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(
                Joiner.on(LN).join("Cначала нужно выбрать коталог в пункте 1.", "", "")));
    }

    @Test
    public void whenYouChooseFirstOptionThanSecondOption() throws IOException {
        var socket = mock(Socket.class);
        var out = new ByteArrayOutputStream();
        var in = new ByteArrayInputStream((firstPath + Joiner.on(LN).join("2", "Папка 1", "exit")).getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        var server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(firstAnswer
                + Joiner.on(LN).join("Вы выбрали команду номер 2. Перейти в подкаталог.",
                        "Список файлов:",
                        ".DS_Store",
                        "21.jpg",
                        "22.jpg",
                        "Папка 2",
                        "",
                        "")));
    }

    @Test
    public void whenYouChooseFirstOptionThanThirdOption() throws IOException {
        var socket = mock(Socket.class);
        var out = new ByteArrayOutputStream();
        var in = new ByteArrayInputStream((firstPath + Joiner.on(LN).join("3", "exit")).getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        var server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(firstAnswer
                + Joiner.on(LN).join("Вы выбрали команду номер 3. Спуститься в родительский католог.",
                        "Список файлов:",
                        ".DS_Store",
                        "oracleSaysTest.txt",
                        "dialog.log",
                        "fileManager",
                        "oracleSays.txt",
                        "test.txt",
                        "log4j.properties",
                        "",
                        "")));
    }

    @Test
    public void whenYouChooseFirstOptionThanFourthOption() throws IOException {
        var socket = mock(Socket.class);
        var out = new ByteArrayOutputStream();
        var in = new ByteArrayInputStream((firstPath + Joiner.on(LN).join("4", "3.png", "exit")).getBytes());
        var oldPath = "./src/main/resources/fileManager/3.png";
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        var server = new Server(socket);
        server.start();
        String oldFile = out.toString();
        oldFile = oldFile.replaceAll(firstAnswer, "");
        oldFile = oldFile.replaceAll("Файл загружен." + "\n" + "\n", "");
        var fiss = new FileInputStream(oldPath);
        int c = 0;
        String newFile = "";
        while ((c = fiss.read()) >= 0) {
            newFile += (char) c;
        }
        newFile = new File(oldPath).length() + "\n" + newFile;
        assertThat(oldFile, is(newFile));
    }
}

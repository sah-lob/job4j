package ru.job4j.bot.server;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenAskAnswerThenChooseRandom() throws IOException {
        var socket = mock(Socket.class);
        var out = new ByteArrayOutputStream();
        var in = new ByteArrayInputStream(Joiner.on(LN).join("hello", "exit").getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        var server = new Server("oracleSays.txt", socket);
        server.start();
        assertThat(out.toString(), is(
                Joiner.on(LN).join("Hello, dear friend, I'm a oracle.", "", "")));
    }

    @Test
    public void whenAskThenChooseAnswerFromDocument() throws IOException {
        var socket = mock(Socket.class);
        var out = new ByteArrayOutputStream();
        var in = new ByteArrayInputStream(Joiner.on(LN).join("test", "exit").getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        var server = new Server("oracleSaysTest.txt", socket);
        server.start();
        assertThat(out.toString(), is(
                Joiner.on(LN).join("Тестовый ответ!", "", "")));
    }

}
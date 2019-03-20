package ru.job4j.filemanager;

import com.google.common.base.Joiner;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final int port;
    private final String ip;
    Scanner console = new Scanner(System.in);
//    Socket socket = new Socket(InetAddress.getByName(ip), port);
//    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//    String ln = System.getProperty("line.separator");

    public static void main(String[] args) throws IOException {
        var client = new Client("localhost", 5100);
        client.start();
    }

    public Client(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
    }

    public void start() throws IOException {
        var socket = new Socket(InetAddress.getByName(ip), port);
        var out = new PrintWriter(socket.getOutputStream(), true);
        var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        var ln = System.getProperty("line.separator");
        String ask;
        do {
            System.out.println(
                    Joiner.on(ln).join(
                            "Введите цифру из меню:",
                            "  1. Получить список корневого каталога. Корневой каталог задается при запуске сервера.",
                            "  2. Перейти в подкаталог.",
                            "  3. Спуститься в родительский каталог",
                            "  4. Скачать файл",
                            "  5. Загрузить файл.",
                            " exit. Выход"));
            ask = console.next();
            out.println(ask);
            if (ask.equals("1")) {
                filePath(out);
            } else if ("2".equals(ask)) {
                subDirectory(out);
            } else if ("4".equals(ask)) {
                downloadFile(ln, out, in);
            } else if ("5".equals(ask)) {
                uploadFile(ln, out);
            }
            var str = in.readLine();
            while (!"exit".equals(ask) && !str.isEmpty()) {
                System.out.println(str);
            }
        } while (!"exit".equals(ask));
    }

    public void filePath(PrintWriter out) {
        System.out.println("Введите путь к корневому каталогу: ");
        out.println(console.next());
    }

    public void subDirectory(PrintWriter out) {
        System.out.println("Введите название подкаталога.");
        out.println(console.next());
    }

    public void downloadFile(String ln, PrintWriter out, BufferedReader in) throws IOException {
        Joiner.on(ln).join("Вы выбрали команду номер 4. Скачать файл.", "Ввыедите название файла.");
        String ask = console.next();
        out.println(ask);
        System.out.println("Введите деррикторию, куда хотите сохранить файл.");
        var addres = console.next();
        var fous = new FileOutputStream(addres + "/" + ask);
        int c;
        int i = 0;
        int len = Integer.parseInt(in.readLine());
        while (i < len) {
            i++;
            c = in.read();
            fous.write((char) c);
            fous.flush();
        }
    }

    public void uploadFile(String ln, PrintWriter out) throws IOException {
        Joiner.on(ln).join("Вы выбрали командру номер 5. Загрузить файл.", "Введите путь к файлу, который хотели скачать.");
        var addres = console.next();
        var file = new File(addres);
        var fileInputStream = new FileInputStream(file);
        System.out.println("Файл будет сохранен в текущую деррикторию.");
        out.println(file.length());
        out.println(file.getName());
        int c;
        while ((c = fileInputStream.read()) >= 0) {
            out.write((char) c);
            out.flush();
        }
        System.out.println("Файл передан.");
    }
}


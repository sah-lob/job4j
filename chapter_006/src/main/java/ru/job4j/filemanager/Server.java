package ru.job4j.filemanager;

import ru.job4j.inputoutput.Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    private File file;
    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(new ServerSocket(5100).accept());
        server.start();
    }

    public void start() throws IOException {
        var out = new PrintWriter(socket.getOutputStream(), true);
        var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String ask;
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);
            if ("1".equals(ask)) {
                filePath(out, in);
            } else if ("2".equals(ask)) {
                subDirectory(out, in);
            } else if ("3".equals(ask)) {
                parentDirectory(out);
            } else if ("4".equals(ask)) {
               downloadFile(out, in);
            } else if ("5".equals(ask)) {
                uploadFile(out, in);
            } else if (!"exit".equals(ask)) {
                out.println("Такой команды не существует выберете команду еще раз.");
                out.println();
            }
        } while (!"exit".equals(ask));

    }

    public String[] getPath(String path) {
        file = new File(path);
        var strings = new String[file.listFiles().length];
        var i = 0;
        for (File f: file.listFiles()) {
            strings[i++] = f.getName();
        }
        return strings;
    }

    public String[] goToChild(String name) {
        String[] strings = null;
        for (File f: file.listFiles()) {
            if (f.getName().equals(name)) {
                if (f.isDirectory()) {
                    strings = getPath(f.getPath());
                    file = f;
                    break;
                }
            }
        }
        if (strings == null) {
            strings = getPath(file.getPath());
        }
        return strings;
    }


    public void filePath(PrintWriter out, BufferedReader in) throws IOException {
        out.println("Вы выбрали команду номер 1. Список каталога.");
        String ask = in.readLine();
        System.out.println(ask);
        out.println("Список файлов:");
        String[] strings = getPath(ask);
        for (String st : strings) {
            out.println(st);
        }
        out.println();
    }

    public void subDirectory(PrintWriter out, BufferedReader in) throws IOException {
        if (file == null) {
            out.println("Cначала нужно выбрать коталог в пункте 1.");
        } else {
            out.println("Вы выбрали команду номер 2. Перейти в подкаталог.");
            var ask = in.readLine();
            System.out.println(ask);
            out.println("Список файлов:");
            String[] strings = goToChild(ask);
            for (String st : strings) {
                out.println(st);
            }
        }
        out.println();
    }

    public void parentDirectory(PrintWriter out) {
        out.println("Вы выбрали команду номер 3. Спуститься в родительский католог.");
        file = file.getParentFile();
        out.println("Список файлов:");
        String[] strings = getPath(file.getPath());
        for (String st : strings) {
            out.println(st);
        }
        out.println();
    }

    public void downloadFile(PrintWriter out, BufferedReader in) throws IOException {
        var ask = in.readLine();
        var f = new File(file.getPath() + "/" + ask);
        System.out.println();
        var fiss = new FileInputStream(f.getPath());
        long len = f.length();
        out.println(len);
        int c;
        while ((c = fiss.read()) >= 0) {
            out.write((char) c);
            out.flush();
        }
        System.out.println("Файлик загружен.");
        out.println("Файл загружен.");
        out.println();
    }

    public void uploadFile(PrintWriter out, BufferedReader in) throws IOException {
        int c;
        int i = 0;
        int len = Integer.parseInt(in.readLine());
        var name = in.readLine();
        var fous = new FileOutputStream(file.getPath() + "/" + name);
        while (i < len) {
            i++;
            c = in.read();
            fous.write(c);
            fous.flush();
        }
        System.out.println("Файл загружен.");
        out.println("Все ок!");
        out.println();
    }

}

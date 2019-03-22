package ru.job4j.filemanager;

import com.google.common.base.Joiner;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    private Scanner console = new Scanner(System.in);
    private List<UserAction> actions = new ArrayList<>();
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String ln = System.getProperty("line.separator");
    private ActionManager actionManager = new ActionManager();

    public static void main(String[] args) throws IOException {
        var client = new Client("localhost", 5100);
        client.start();
    }

    public Client(String ip, int port) throws IOException {
        this.socket = new Socket(InetAddress.getByName(ip), port);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void start() throws IOException {
        actions = actionManager.fillActions(new FilePath(), new SubDirectory(), new ParentDirectory(), new DownloadFile(), new UploadFile());
        String ask;
        do {
            showMenu();
            ask = console.next();
            out.println(ask);
            actionManager.select(ask, actions, out);
            var str = in.readLine();
            while (!"exit".equals(ask) && !str.isEmpty()) {
                System.out.println(str);
            }
        } while (!"exit".equals(ask));
    }

    public void showMenu() {
        System.out.println("Введите цифру из меню:");
        for (UserAction a : actions) {
            System.out.println(a.info());
        }
        System.out.println(" exit. Выход");
    }

    public class FilePath implements UserAction {
        @Override
        public void execute() {
            System.out.println("Введите путь к корневому каталогу: ");
            out.println(console.next());
        }

        @Override
        public String info() {
            return " 1. Получить список корневого каталога. Корневой каталог задается при запуске сервера.";
        }

    }

    public class SubDirectory implements UserAction {
        @Override
        public void execute() {
            System.out.println("Введите название подкаталога.");
            out.println(console.next());
        }

        @Override
        public String info() {
            return " 2. Перейти в подкаталог.";
        }
    }

    public class ParentDirectory implements UserAction {
        @Override
        public void execute() {
        }

        @Override
        public String info() {
            return " 3. Спуститься в родительский каталог";
        }
    }

    public class DownloadFile implements UserAction {
        @Override
        public void execute() throws IOException {
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

        @Override
        public String info() {
            return " 4. Скачать файл";
        }
    }

    public class UploadFile implements UserAction {
        @Override
        public void execute() throws IOException {
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

        @Override
        public String info() {
            return " 5. Загрузить файл.";
        }
    }
}


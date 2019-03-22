package ru.job4j.filemanager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {

    private File file;
    private PrintWriter out;
    private BufferedReader in;
    private List<UserAction> actions = new ArrayList<>();
    private ActionManager actionManager = new ActionManager();


    public Server(Socket socket) {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(new ServerSocket(5100).accept());
        server.start();
    }

    public void start() throws IOException {
        actions = actionManager.fillActions(new FilePath(), new SubDirectory(), new ParentDirectory(), new DownloadFile(), new UploadFile());
        String ask;
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);
            actionManager.select(ask, actions, out);
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

    public class FilePath implements UserAction {
        @Override
        public void execute() throws IOException {
            out.println(info());
            String ask = in.readLine();
            System.out.println(ask);
            out.println("Список файлов:");
            String[] strings = getPath(ask);
            for (String st : strings) {
                out.println(st);
            }
            out.println();
        }

        @Override
        public String info() {
            return "Вы выбрали команду номер 1. Список каталога.";
        }
    }

    public class SubDirectory implements UserAction {
        @Override
        public void execute() throws IOException {
            if (file == null) {
                out.println("Cначала нужно выбрать коталог в пункте 1.");
            } else {
                out.println(info());
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

        @Override
        public String info() {
            return "Вы выбрали команду номер 2. Перейти в подкаталог.";
        }
    }

    public class ParentDirectory implements UserAction {

        @Override
        public void execute() throws IOException {
            out.println(info());
            file = file.getParentFile();
            out.println("Список файлов:");
            String[] strings = getPath(file.getPath());
            for (String st : strings) {
                out.println(st);
            }
            out.println();
        }

        @Override
        public String info() {
            return "Вы выбрали команду номер 3. Спуститься в родительский католог.";
        }
    }

    public class DownloadFile implements UserAction {

        @Override
        public void execute() throws IOException {
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

        @Override
        public String info() {
            return null;
        }
    }

    public class UploadFile implements UserAction {
        @Override
        public void execute() throws IOException {
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

        @Override
        public String info() {
            return null;
        }
    }
}

package ru.job4j.bot.server;

import ru.job4j.inputoutput.Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Server {

    private String fileName;
    private final Socket socket;



    public static void main(String[] args) throws IOException {
        Socket socket = new ServerSocket(5000).accept();
        Server server = new Server("oracleSays.txt", socket);
        server.start();
    }

    public Server(String fileName, Socket socket) {
        this.fileName = fileName;
        this.socket = socket;
    }

    public void start() throws IOException {

        var out = new PrintWriter(socket.getOutputStream(), true);
        var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        var answers = readingFile();

        String ask;
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);
            if ("hello".equals(ask)) {
                out.println("Hello, dear friend, I'm a oracle.");
                out.println();
            } else if (!"exit".equals(ask)) {
                out.println(answers.get(new Random().nextInt(answers.size())));
                out.println();
            }
        } while (!"exit".equals(ask));

    }


    public ArrayList<String> readingFile() {
        Scanner sc = null;
        try {
            var classLoader = Chat.class.getClassLoader();
            sc = new Scanner(classLoader.getResourceAsStream(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        var splitted = new ArrayList<String>();
        while (sc.hasNext()) {
            splitted.add(sc.nextLine());
        }
        return  splitted;
    }
}

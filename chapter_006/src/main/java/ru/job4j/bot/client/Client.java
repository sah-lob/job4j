package ru.job4j.bot.client;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final Socket socket;

    public static void main(String[] args) throws IOException {
        Client client = new Client(new Socket(InetAddress.getByName("localhost"), 5000));
        client.start();
    }

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {

        var out = new PrintWriter(socket.getOutputStream(), true);
        var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        var console = new Scanner(System.in);
        String ask;
        do {
            ask = console.next();
            out.println(ask);
            var str = in.readLine();
            while (!str.isEmpty()) {
                System.out.println(str);
            }
        } while (!"exit".equals(ask));
    }
}

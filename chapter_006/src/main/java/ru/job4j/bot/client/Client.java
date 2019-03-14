package ru.job4j.bot.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    private final int port;
    private final String ip;

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 5000);
        client.start();
    }

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() throws IOException {
        var socket = new Socket(InetAddress.getByName(ip), port);
        var out = new PrintWriter(socket.getOutputStream(), true);
        var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        var console = new Scanner(System.in);
        var ask = "";
        do {
            ask = console.next();
            out.println(ask);
            var str = in.readLine();
            while (!"exit".equals(ask) && !str.isEmpty()) {
                System.out.println(str);
            }
        } while (!"exit".equals(ask));
    }
}

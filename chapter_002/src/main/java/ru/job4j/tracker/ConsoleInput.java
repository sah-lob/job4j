package ru.job4j.tracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput implements Input {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    @Override
    public String ask(String question) {
        String answer = "";
        System.out.println(question);
        try {
            answer = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  answer;
    }
}

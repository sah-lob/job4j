package ru.job4j.inputoutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Chat {

    public static void main(String[] args) {
        var chat = new Chat();
        chat.chatMaintenance("test.txt");
    }

    private Logger logger = LoggerFactory.getLogger(Chat.class);

    public void chatMaintenance(String fileName) {

        logger.info("Чат бот запущен.");

        var flag = true;
        var word = new Scanner(new InputStreamReader(System.in)).next();
        var phrases = readLines(fileName);

        while (!"закончить".equals(word)) {
            logger.info("Вы: " + word);
            if ("стоп".equals(word)) {
                logger.info("Бот: Была введена команда стоп.");
                flag = false;
            } else if ("продолжить".equals(word)) {
                logger.info("Бот: Была введена команда продолжить.");
                flag = true;
            }
            if (flag) {
                int num = new Random().nextInt(phrases.size() - 1);
                System.out.println(phrases.get(num));
                logger.info("Бот: " + phrases.get(num));
            }
            word = new Scanner(new InputStreamReader(System.in)).next();
        }
        logger.info("Бот: Была введена команда закончить.");
        logger.info("Чат бот остановлен.");
    }

    public ArrayList<String> readLines(String fileName) {
        var splitted = new ArrayList<String>();
        try {
            var classLoader = Chat.class.getClassLoader();
            var sc = new Scanner(classLoader.getResourceAsStream(fileName));
            while (sc.hasNext()) {
                splitted.add(sc.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  splitted;
    }
}

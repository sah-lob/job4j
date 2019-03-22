package ru.job4j.filemanager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ActionManager {

    public void select(String key, List<UserAction> actions, PrintWriter out) throws IOException {
        if (!"exit".equals(key)) {
            var flag = true;
            if (key.matches("[-+]?\\d+")) {
                int k = Integer.parseInt(key) - 1;
                if (k >= 0 && k < 5) {
                    actions.get(k).execute();
                    flag = false;
                }
            }
            if (flag) {
                out.println("Такой команды не существует. Выберете другую команду.");
                out.println();
            }
        }
    }


    public List<UserAction>  fillActions(UserAction filePath, UserAction subDirectory, UserAction parentDirectory, UserAction downloadFile, UserAction uploadFile) {
        List<UserAction> actions = new ArrayList<>();
        actions.add(filePath);
        actions.add(subDirectory);
        actions.add(parentDirectory);
        actions.add(downloadFile);
        actions.add(uploadFile);
        return actions;
    }
}
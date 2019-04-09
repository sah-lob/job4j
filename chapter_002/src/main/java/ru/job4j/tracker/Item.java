package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Заявка
 *
 * Включает в себя:
 * id - id заявки
 * name - имя заявки
 * desc - описание заявки
 * dateOfCreation - дата создания заявки
 * commtnts - комментарии, оставленные к заявке.
 */



public class Item {

    private String id;
    private String name;
    private String desc;
    private String dateOfCreation;
    private ArrayList<String> comments;

    /**
     * Конструктор.
     */
    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.dateOfCreation = String.valueOf(new Date());
        this.comments = new ArrayList<>();
    }


    /**
     *Ниже идут геттеры и сеттеры.
     */

    public void setId(String generateId) {
        id = generateId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getDesc() {
        return desc;
    }

    public ArrayList<String> getComments() {
        return comments;
    }


    public String getDateOfCreation() {
        return dateOfCreation;
    }
}

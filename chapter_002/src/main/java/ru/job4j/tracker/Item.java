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
    private Date dateOfCreation;
    private ArrayList<String> comments;

    /**
     * Конструктор.
     */
    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.dateOfCreation = new Date();
        this.comments = new ArrayList<>();
    }


    /**
     *Ниже идут геттеры и сеттеры.
     */

    public void setId(String generateId) {
        id = generateId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }
}

package ru.job4j.vacancy;

import java.util.Date;

class Vacancy {
    private String name;
    private String text;
    private String link;
    private Date data;

    public Vacancy(String name, String text, String link, Date data) {
        this.name = name;
        this.text = text;
        this.link = link;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public Date getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Заголовок: " + name + "\nСсылка: " + link + "\nОписание: " + text + "\nДата: " + data + "\n\n";
    }
}
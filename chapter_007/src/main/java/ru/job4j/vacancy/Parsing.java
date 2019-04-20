package ru.job4j.vacancy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Parsing {

    public ArrayList<Vacancy> startParsing(Date date) {
        System.out.println("Начинаем парсинг сайта.");
        var vacancies = new ArrayList<Vacancy>();
        var dateFlag = new AtomicReference<>(true);
        var numberOfPages = new AtomicInteger(1);
        while (dateFlag.get()) {
            Document document = null;
            try {
                document = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + numberOfPages.toString()).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            var h2elements = document.getElementsByTag("tr");
            for (int i = 0; i < 7; i++) {
                h2elements.remove(0);
                if (i < 3) {
                    h2elements.remove(h2elements.last());
                }
            }
            h2elements.forEach(h2element -> {
                var aElement = h2element.child(1);
                var title = aElement.child(0).text();
                var data = stringToDate(h2element.child(5).text());
                if (date.before(data)) {
                    if (javaTest(title)) {
                        var url = aElement.child(0).attr("href");
                        var text = "";
                        try {
                            var document1 = Jsoup.connect(url).get();
                            var element = document1.getElementsByAttributeValue("class", "msgBody").get(1);
                            text = element.text();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        vacancies.add(new Vacancy(title, text, url, data));
                    }
                } else {
                    dateFlag.set(false);
                }
            });
            numberOfPages.getAndIncrement();
        }
        System.out.println("Парсинг сайта завершен.");
        return vacancies;
    }

    private Date stringToDate(String str) {
        Date date = null;
        var dateFormat = new SimpleDateFormat("d MMM yy',' HH:mm");
        var data = str.split(" ");
        if (data[0].equals("вчера,")) {
            var cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_MONTH, -1);
            var s1 = data[1].split(":");
            date = cal.getTime();
            date.setHours(Integer.parseInt(s1[0]));
            date.setMinutes(Integer.parseInt(s1[1]));
        } else if (data[0].equals("сегодня,")) {
            var cal = Calendar.getInstance();
            cal.setTime(new Date());
            date = cal.getTime();
            String[] s1 = data[1].split(":");
            date.setHours(Integer.parseInt(s1[0]));
            date.setMinutes(Integer.parseInt(s1[1]));
        } else {
            switch (data[1]) {
                case ("фев"): data[1] = "февр.";
                    break;
                case ("ноя"): data[1] = "нояб.";
                    break;
                case ("сен"): data[1] = "сент.";
                    break;
                case ("май"): break;
                default: data[1] += ".";
            }
            str = "";
            for (var os :data) {
                str += os + " ";
            }
            str = str.substring(0, str.length() - 1);
            try {
                date = dateFormat.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        var timestamp = new Timestamp(date.getTime());
        timestamp.setNanos(0);
        timestamp.setSeconds(0);
        date = timestamp;
        return date;
    }

    private boolean javaTest(String name) {
        var flag = false;
        name = name.toUpperCase();
        if (name.contains("JAVA")) {
            if (!name.contains("JAVASCRIPT") && !name.contains("JAVA SCRIPT")) {
                flag = true;
            }
        }
        return flag;
    }
}

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
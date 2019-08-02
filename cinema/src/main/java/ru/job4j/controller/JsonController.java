package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.persistence.DBUsersStore;
import ru.job4j.persistence.Person;
import ru.job4j.persistence.UsersStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonController extends HttpServlet {

    private final Map<String, Person> persons = new ConcurrentHashMap();
    private UsersStore dbUsersStore = DBUsersStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        var sb = new StringBuilder();
        try (var reader = req.getReader()) {
            if (reader != null) {
                sb.append(reader.readLine());
            }
        }
        var mapper = new ObjectMapper();
        var person = mapper.readValue(sb.toString(), Person.class);
        System.out.println(person.getDescription());
        if (person.getDescription().equals("N")) {
            if (dbUsersStore.isExists(person.getLogin())) {
                var s = "Данный логин уже зарегистрирован.";
                System.out.println(s);
                resp.getWriter().write(s);
            } else {
                dbUsersStore.add(person);
                this.persons.put(person.getLogin(), person);
                req.getSession().setAttribute("login", person.getLogin());
            }
        } else {
            if (dbUsersStore.validatePerson(person)) {
                System.out.println("все верно!!!");
                req.getSession().setAttribute("login", person.getLogin());
            } else if (!dbUsersStore.isExists(person.getLogin())) {
                var s = "Такого логина нет!";
                System.out.println(s);
                resp.getWriter().write(s);
            } else {
                var s = "Неправильный пароль.";
                System.out.println(s);
                resp.getWriter().write(s);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        var pw = resp.getWriter();
        var login = req.getSession().getAttribute("login").toString();
        var fioAndNumber = dbUsersStore.getFioAndNumber(login);
        var name = fioAndNumber[0];
        var phone = fioAndNumber[1];
        var objectMapper = new ObjectMapper();
        var arrayJson = objectMapper.createArrayNode();
        var node = objectMapper.createObjectNode();
        node.put("login", login);
        node.put("name", name);
        node.put("phone", phone);
        arrayJson.add(node);
        pw.append(objectMapper.writeValueAsString(arrayJson));
        pw.flush();
    }
}
package ru.job4j.usersmodel;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class JsonController extends HttpServlet {

    private final Map<Integer, Person> persons = new ConcurrentHashMap();
    private final AtomicInteger id = new AtomicInteger(0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        var writer = new PrintWriter(resp.getOutputStream());
        var mapper = new ObjectMapper();
        mapper.writeValue(writer, persons);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var sb = new StringBuilder();
        try (var reader = req.getReader()) {
            if (reader != null) {
                sb.append(reader.readLine());
            }
        }
        var mapper = new ObjectMapper();
        var person = mapper.readValue(sb.toString(), Person.class);
        this.persons.put(id.getAndIncrement(), person);
    }
}

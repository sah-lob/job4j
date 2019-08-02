package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.job4j.persistence.DBSeatsStore;
import ru.job4j.persistence.DBUsersStore;
import ru.job4j.persistence.Person;
import ru.job4j.persistence.Seat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class HallServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        var reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        ObjectMapper mapper = new ObjectMapper();
        var node = mapper.readTree(sb.toString());
        int row = node.get("row").asInt();
        int place = node.get("place").asInt();
        var fio = node.get("username").asText();
        var number = node.get("phone").asText();
        new DBUsersStore().editPerson(req.getSession().getAttribute("login").toString(), fio, number);
        new DBSeatsStore().update(req.getSession().getAttribute("login").toString(), row, place);
        ObjectNode resNode = mapper.createObjectNode();
        resNode.put("success", true).put("row", row).put("place", place);
        var pw = resp.getWriter();
        pw.append(mapper.writeValueAsString(resNode));
        pw.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        var pw = resp.getWriter();
        var dbSeatsStore = DBSeatsStore.getInstance();
        var seats = dbSeatsStore.findAll();
        var seatMap = new TreeMap<Integer, Set<Seat>>();
        for (var seat : seats) {
            int row = seat.getRow();
            if (!seatMap.containsKey(row) || seatMap.get(row) == null) {
                seatMap.put(row, new TreeSet<>(Set.of(seat)));
            } else {
                seatMap.get(row).add(seat);
            }
        }
        var objectMapper = new ObjectMapper();
        var arrayJson = objectMapper.createArrayNode();
        for (var entry : seatMap.entrySet()) {
            var node = objectMapper.createObjectNode();
            var arrayNode = objectMapper.createArrayNode();
            node.put("row", entry.getKey());
            for (var entry2 : entry.getValue()) {
                ObjectNode innerNode = objectMapper.createObjectNode();
                innerNode.put("place", entry2.getNumber());
                innerNode.put("price", entry2.getPrice());
                innerNode.put("price", entry2.getPrice());
                innerNode.put("sold", entry2.isBusy());
                innerNode.put("login", entry2.getLogin());
                arrayNode.add(innerNode);
            }
            node.set("seats", arrayNode);
            arrayJson.add(node);
        }
        pw.append(objectMapper.writeValueAsString(arrayJson));
        pw.flush();
    }
}

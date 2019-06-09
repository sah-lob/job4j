package ru.job4j.usersmodel.presentation;

import ru.job4j.usersmodel.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsersServlet extends HttpServlet {
    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var printWriter = new PrintWriter(resp.getOutputStream());
        var sb = new StringBuilder();
        sb.append("<table>");
        var i = 0;
        for (var l : validateService.findAll()) {
            if (i == 0) {
                sb.append("<tr>"
                        + "<td>#"
                        + "</td>"
                        + "<td>ID"
                        + "</td>"
                        + "<td> Name"
                        + "</td>"
                        + "<td> Login"
                        + "</td>"
                        + "<td> Email"
                        + "</td>"
                        + "<td> CreateDate"
                        + "</td>"
                        + "<td>"
                        + "</td>"
                        + "<td>"
                        + "</td>"
                        + "</tr>");
            }
            sb.append("<tr>"
                    + "<td>" + i
                    + "</td>"
                    + "<td>" + l.getId()
                    + "</td>"
                    + "<td>" + l.getName()
                    + "</td>"
                    + "<td>" + l.getLogin()
                    + "</td>"
                    + "<td>" + l.getEmail()
                    + "</td>"
                    + "<td>" + l.getCreateDate()
                    + "</td>"
                    + "<td>"
                    + "<form action='" + req.getContextPath() + "/update' method='get'>"
                    + "<button name='id' type='hidden' value=" + l.getId() + ">Update"
                    + "</button>"
                    + "</form>"
                    + "</td>"
                    + "<td>"
                    + "<form action='" + req.getContextPath() + "/list' method='post'>"
                    + "<button name='id' type='hidden' value=" + l.getId() + ">Delete"
                    + "</button>"
                    + "</form>"
                    + "</td>"
                    + "</tr>");
            i++;
        }
        sb.append("</table>");
        printWriter.append(
                "<!DOCTYPE html>"
                        + "<html lang=\"en\">"
                        + "<head>"
                        + "   <meta charset=\"UTF-8\">"
                        + "Список пользователей:"
                        + "   <title>Список</title>"
                        + "</head>"
                        + "<body>"
                        + "<form action ='" + req.getContextPath() + "/create' method = 'get'>"
                        + "<input type= 'submit' value='Добавить'>"
                        + "</form>"
                        + "<br/>"
                        + sb.toString()
                        + "</body>"
                        + "</html>");
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        validateService.delete(req.getParameter("id"));
        resp.sendRedirect(req.getContextPath() + "/list");
    }
}

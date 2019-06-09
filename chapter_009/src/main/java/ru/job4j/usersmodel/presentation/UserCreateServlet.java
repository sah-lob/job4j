package ru.job4j.usersmodel.presentation;

import ru.job4j.usersmodel.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class UserCreateServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var printWriter = new PrintWriter(resp.getOutputStream());
        var sb = new StringBuilder();
        sb.append("<table>");
        for (var l : validateService.findAll()) {
            sb.append("<tr><td>" + l + "</td></tr>");
        }
        sb.append("</table>");
        printWriter.append(
                "<!DOCTYPE html>"
                        + "<html lang=\"en\">"
                        + "<head>"
                        + "   <meta charset=\"UTF-8\">"
                        + "Добавление нового пользователя."
                        + "   <title>Добавление</title>"
                        + "</head>"
                        + "<body>"
                        + "<form action ='" + req.getContextPath() + "/create' method ='post'>"
                        + "<br/>"
                        + "<table>"
                        + "<tr>" + "<td>" + "Name : <input type='text' name = 'name'/>" + "</td>" + "</tr>"
                        + "<tr>" + "<td>" + "Login : <input type='text' name = 'login'/>" + "</td>" + "</tr>"
                        + "<tr>" + "<td>" + "Email : <input type='text' name = 'email'/>" + "</td>" + "</tr>"
                        + "</table>"
                        + "<input type='submit'>"
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
        var createDate = new Date().toString();
        validateService.add(req.getParameter("name"), req.getParameter("login"),
                req.getParameter("email"), createDate);
        resp.sendRedirect(req.getContextPath() + "/list");
    }
}

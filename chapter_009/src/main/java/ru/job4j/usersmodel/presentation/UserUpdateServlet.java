package ru.job4j.usersmodel.presentation;
import ru.job4j.usersmodel.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var writer = new PrintWriter(resp.getOutputStream());
        var id = req.getParameter("id");
        var user = validateService.findById(id);
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Update of user</title>"
                + "</head>"
                + "<br/>"
                + "<form action='" + req.getContextPath() + "/list' method='get'>"
                + "<input type='submit' value='Вернуться к списку пользователей.'>"
                + "</form>"
                + "<table>"
                + "<tr>"
                + "<td>"
                + "Parameter's  user: " + user.getLogin()
                + "<form action = ' " + req.getContextPath() + "/update' method='post'>"
                + "Name : <input type = 'text' value=" + user.getName() + " name='name'/>"
                + "Login : <input type = 'text' value=" + user.getLogin() + " name='login'/>"
                + "Email : <input type = 'text' value=" + user.getEmail() + " name='email'/>"
                + "<button name='id' type='hidden' value=" + user.getId() + ">Продолжить"
                + "</button>"
                + "</form>"
                + "</td></tr>"
                + "</table>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        validateService.update(req.getParameter("id"), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        resp.sendRedirect(req.getContextPath() + "/list");
    }
}

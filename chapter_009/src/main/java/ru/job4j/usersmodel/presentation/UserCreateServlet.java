package ru.job4j.usersmodel.presentation;

import ru.job4j.usersmodel.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class UserCreateServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var createDate = new Date().toString();
        validateService.add(req.getParameter("name"), req.getParameter("login"),
                req.getParameter("email"), createDate);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}

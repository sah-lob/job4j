package ru.job4j.usersmodel.presentation;

import ru.job4j.usersmodel.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersServlet extends HttpServlet {
    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        int id = Integer.valueOf(String.valueOf(session.getAttribute("id")));
        var admin = validateService.findById(String.valueOf(session.getAttribute("id"))).isAdmin();
        req.setAttribute("users", validateService.findAll());
        req.setAttribute("admin", admin);
        req.setAttribute("id", id);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var session = req.getSession();
        if (String.valueOf(session.getAttribute("id")).equals(req.getParameter("id"))) {
            session.invalidate();
        }
        validateService.delete(req.getParameter("id"));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}

package ru.job4j.usersmodel.presentation;
import ru.job4j.usersmodel.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", validateService.findById(req.getParameter("id")));
        req.getRequestDispatcher("/WEB-INF/views/upd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        validateService.update(req.getParameter("id"), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}

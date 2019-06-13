package ru.job4j.usersmodel.presentation;
import ru.job4j.usersmodel.logic.Validate;
import ru.job4j.usersmodel.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {

    private final Validate validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        var admin = validateService.findById(String.valueOf(session.getAttribute("id"))).isAdmin();
        req.setAttribute("admin", admin);
        req.setAttribute("user", validateService.findById(req.getParameter("id")));
        req.getRequestDispatcher("/WEB-INF/views/upd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        boolean admin;
        System.out.println(req.getParameter("admin"));
        if (req.getParameter("admin") == null) {
            admin = false;
        } else {
            admin = req.getParameter("admin").equals("yes");
        }
        validateService.update(req.getParameter("id"), req.getParameter("name"),
                req.getParameter("login"), req.getParameter("email"), admin, req.getParameter("password"));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}

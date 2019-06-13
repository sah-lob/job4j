package ru.job4j.usersmodel.presentation;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Signout extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        synchronized (session) {
            session.invalidate();
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}

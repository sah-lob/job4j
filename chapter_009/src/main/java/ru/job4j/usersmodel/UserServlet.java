package ru.job4j.usersmodel;

import ru.job4j.usersmodel.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class UserServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        var action = req.getParameter("action");
//        if (action != null) {
//            switch (action) {
//                case ("add") :
//                    var createDate = new Date().toString();
//                    validateService.add(req.getParameter("name"), req.getParameter("login"),
//                            req.getParameter("email"), createDate);
//                    break;
//                case ("update") :
//                    validateService.update(req.getParameter("id"), req.getParameter("login"), req.getParameter("name"), req.getParameter("email"));
//                    break;
//                case ("delete") :
//                    validateService.delete(req.getParameter("id"));
//                    break;
//                default :
//                    System.out.println("Вы ввели неверную команду.");
//                    break;
//            }
//        }
//        doGet(req, resp);

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter writer = new PrintWriter(res.getOutputStream());
        List<User> list = validateService.findAll();
        writer.append("usersList:");
        for (var v: list) {
            writer.append(v.toString());
            writer.append("\n");
        }
        writer.flush();
    }
}

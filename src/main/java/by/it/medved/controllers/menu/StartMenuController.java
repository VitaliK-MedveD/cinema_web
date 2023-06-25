package by.it.medved.controllers.menu;

import by.it.medved.util.Link;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/start")
public class StartMenuController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String link1 = req.getParameter("link1");
        String link2 = req.getParameter("link2");
        if (link1 != null) {
            req.getRequestDispatcher(Link.USER_SING_IN_PAGE).forward(req, resp);
        } if (link2 != null) {
            req.getRequestDispatcher(Link.USER_REGISTRATION_PAGE).forward(req, resp);
        }
    }
}
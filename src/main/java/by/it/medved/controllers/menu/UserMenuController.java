package by.it.medved.controllers.menu;

import by.it.medved.util.Link;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/menu")
public class UserMenuController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("action")) {
            case "editProfile" : {
                req.getRequestDispatcher(Link.USER_EDIT_PAGE).forward(req, resp);
                break;
            }
            case "exit" : {
                req.getRequestDispatcher("/exit").forward(req, resp);
                break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

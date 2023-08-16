package by.it.medved.controllers.menu;

import by.it.medved.entities.User;
import by.it.medved.util.FieldsEntities;

import static by.it.medved.util.FieldsEntities.USER;
import static by.it.medved.util.Link.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/authorization")
public class AuthorizationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);
        switch (user.getRole()) {
            case USER:
                req.getRequestDispatcher(USER_MENU_PAGE).forward(req, resp);
                break;
            case MANAGER:
                req.getRequestDispatcher(MANAGER_MENU_PAGE).forward(req, resp);
                break;
            case ADMIN:
            case SUPER_ADMIN:
                req.getRequestDispatcher(ADMIN_MENU_PAGE).forward(req, resp);
                break;
        }
    }
}

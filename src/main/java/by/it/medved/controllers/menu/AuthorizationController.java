package by.it.medved.controllers.menu;

import by.it.medved.entities.User;
import by.it.medved.util.Link;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/Authorization")
public class AuthorizationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        switch (user.getAccess()) {
            case USER:
                req.getRequestDispatcher(Link.USER_MENU_PAGE).forward(req, resp);
                break;
            case MANAGER:
                req.getRequestDispatcher("/manager/menu").forward(req, resp);
                break;
            case ADMIN:
                req.getRequestDispatcher("/pages/user/admin-menu.jsp").forward(req, resp);
                break;
        }
    }
}

package by.it.medved.controllers.menu;

import by.it.medved.util.Link;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/edit/user")
public class EditUserMenuController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("action")) {
            case "editAccess" :
                req.getRequestDispatcher("/pages/user/update-user-access.jsp").forward(req, resp);
                break;
            case "delete" :
                req.getRequestDispatcher("/admin/user/delete").forward(req, resp);
                break;
            case "back" :
                req.getRequestDispatcher("/admin/menu").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/user/edit-user-menu.jsp").forward(req, resp);
    }
}

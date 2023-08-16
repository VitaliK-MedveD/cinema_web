package by.it.medved.controllers.menu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.it.medved.util.FieldsEntities.*;
import static by.it.medved.util.Link.*;

@WebServlet(urlPatterns = "/admin/edit/user")
public class EditUserMenuController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("action")) {
            case RETURN_USER_TICKET:
                req.getRequestDispatcher(READ_USER_TICKETS_URI).forward(req, resp);
                break;
            case EDIT_ROLE:
                req.getRequestDispatcher(USER_UPDATE_ROLE_PAGE).forward(req, resp);
                break;
            case DELETE:
                req.getRequestDispatcher(USER_DELETE_URI).forward(req, resp);
                break;
            case BACK :
                doGet(req, resp);
//                req.getRequestDispatcher(AUTHORIZATION_CONTROLLER_URI).forward(req, resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(AUTHORIZATION_CONTROLLER_URI).forward(req, resp);
    }
}

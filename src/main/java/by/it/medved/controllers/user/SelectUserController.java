package by.it.medved.controllers.user;

import by.it.medved.entities.User;
import by.it.medved.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.it.medved.services.UserServiceImpl.getUserService;
import static by.it.medved.util.Link.*;
import static by.it.medved.util.FieldsEntities.*;



@WebServlet(urlPatterns = "/user/select")
public class SelectUserController extends HttpServlet {

    private final UserService userService = getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long userId = Long.parseLong(req.getParameter(USER_ID));
        User selectedUser = userService.getUserById(userId);
        session.setAttribute(SELECTED_USER, selectedUser);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(EDIT_USER_MENU_PAGE).forward(req, resp);
    }
}

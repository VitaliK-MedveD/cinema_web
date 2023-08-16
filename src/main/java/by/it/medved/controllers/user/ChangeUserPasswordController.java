package by.it.medved.controllers.user;

import by.it.medved.entities.User;
import by.it.medved.services.UserService;
import by.it.medved.util.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.it.medved.services.UserServiceImpl.getUserService;
import static by.it.medved.util.FieldsEntities.*;
import static by.it.medved.util.Link.AUTHORIZATION_CONTROLLER_URI;
import static by.it.medved.util.Message.UPDATE_SUCCESSFUL;

@WebServlet(urlPatterns = "/user/change_password")
public class ChangeUserPasswordController extends HttpServlet {

    private final UserService userService = getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);
        String newPassword = req.getParameter(NEW_PASSWORD);
        User updatedUser = userService.changeUserPassword(newPassword, user.getId());
        session.setAttribute(USER, updatedUser);
        req.setAttribute(MESSAGE, UPDATE_SUCCESSFUL);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(AUTHORIZATION_CONTROLLER_URI).forward(req, resp);
    }
}

package by.it.medved.controllers.user;

import by.it.medved.enums.Role;
import by.it.medved.entities.User;
import by.it.medved.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.it.medved.services.UserServiceImpl.getUserService;
import static by.it.medved.util.FieldsEntities.*;
import static by.it.medved.util.Link.*;

@WebServlet(urlPatterns = "/user/read")
public class ReadUsersController extends HttpServlet {

    private final UserService userService = getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);
        List<User> users = userService.getUsers();
        users.removeIf(person -> person.getRole().equals(Role.SUPER_ADMIN));
        users.removeIf(person -> person.getLogin().equals(user.getLogin()));
        req.setAttribute(USERS, users);
        req.getRequestDispatcher(USERS_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

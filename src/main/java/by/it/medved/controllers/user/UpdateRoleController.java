package by.it.medved.controllers.user;

import by.it.medved.entities.Role;
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

@WebServlet(urlPatterns = "/user/update/role")
public class UpdateRoleController extends HttpServlet {

    private final UserService userService = getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);
        if (user.getRole().equals(Role.ADMIN)) {
            User selectedUser = (User) session.getAttribute(SELECTED_USER);
            String role = req.getParameter(ROLE);
            userService.updateRole(selectedUser.getId(), Role.valueOf(role));
            doGet(req, resp);
        } else {
            req.getRequestDispatcher(FORBIDDEN_403_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(USERS_READ_URI).forward(req, resp);
    }
}
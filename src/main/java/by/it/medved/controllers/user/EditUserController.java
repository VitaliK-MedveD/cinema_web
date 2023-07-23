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

@WebServlet(urlPatterns = "/user/edit")
public class EditUserController extends HttpServlet {

    private static final String UPDATE_SUCCESSFUL = "Update successful";
    private final UserService userService = getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);
        String firstName = req.getParameter(FIRST_NAME);
        String email = req.getParameter(EMAIL);
        String dateBirthday = req.getParameter(DATE_BIRTHDAY);
        user = userService.updateUserFields(user.getId(), firstName, email, dateBirthday);
        session.setAttribute(USER, user);
        req.setAttribute(UPDATE_PROFILE_SUCCESSFUL, UPDATE_SUCCESSFUL);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(USER_EDIT_PAGE).forward(req, resp);
    }
}

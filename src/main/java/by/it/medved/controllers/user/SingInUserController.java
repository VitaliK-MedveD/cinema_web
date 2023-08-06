package by.it.medved.controllers.user;

import by.it.medved.entities.User;
import by.it.medved.services.EncryptionService;
import by.it.medved.services.EncryptionServiceImpl;
import by.it.medved.services.UserService;
import org.hibernate.Hibernate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static by.it.medved.services.EncryptionServiceImpl.*;
import static by.it.medved.services.UserServiceImpl.getUserService;
import static by.it.medved.util.Link.*;
import static by.it.medved.util.Message.*;
import static by.it.medved.util.FieldsEntities.*;

@WebServlet(urlPatterns = "/user/singIn")
public class SingInUserController extends HttpServlet {

    private final UserService userService = getUserService();
    private final EncryptionService encryptionService = getEncryptionService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        String errorMessage;

        Optional<User> optionalUser = userService.getUserByLogin(login);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Hibernate.initialize(user.getTickets());
            if (encryptionService.authenticate(password, user.getPassword(), user.getSalt())) {
                HttpSession session = req.getSession();
                session.setAttribute(USER, user);
                req.getRequestDispatcher(AUTHORIZATION_CONTROLLER_URI).forward(req, resp);
            } else {
                errorMessage = INCORRECT_PASSWORD;
                req.setAttribute(ENTERED_LOGIN, login);
                req.setAttribute(ERROR_MESSAGE, errorMessage);
                doGet(req, resp);
            }
        } else {
            errorMessage = LOGIN_NOT_REGISTERED;
            req.setAttribute(ERROR_MESSAGE, errorMessage);
            doGet(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getAttribute(ERROR_MESSAGE);
        req.getRequestDispatcher(USER_SING_IN_PAGE).forward(req, resp);
    }
}
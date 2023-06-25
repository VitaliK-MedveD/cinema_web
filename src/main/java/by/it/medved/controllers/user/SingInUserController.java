package by.it.medved.controllers.user;

import by.it.medved.entities.User;
import by.it.medved.services.EncryptionService;
import by.it.medved.services.EncryptionServiceImpl;
import by.it.medved.services.UserService;
import by.it.medved.services.UserServiceImpl;
import by.it.medved.util.Link;
import by.it.medved.util.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/user/singIn")
public class SingInUserController extends HttpServlet {

    private final UserService userService = new UserServiceImpl();
    private final EncryptionService encryptionService = new EncryptionServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String errorMessage;

        Optional<User> optionalUser = userService.getByLogin(login);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (encryptionService.authenticate(password, user.getPassword(), user.getSalt())) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                req.getRequestDispatcher("/Authorization").forward(req, resp);
            } else {
                errorMessage = Message.INCORRECT_PASSWORD;
                req.setAttribute("enteredLogin", login);
                req.setAttribute("errorMessage", errorMessage);
                doGet(req, resp);
//                req.getRequestDispatcher(Link.USER_SING_IN_URI).forward(req, resp);
            }
        } else {
            errorMessage = Message.LOGIN_NOT_REGISTERED;
            req.setAttribute("errorMessage", errorMessage);
            doGet(req, resp);
//            req.getRequestDispatcher(Link.USER_SING_IN_PAGE).forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getAttribute("errorMessage");
        req.getRequestDispatcher(Link.USER_SING_IN_PAGE).forward(req, resp);
    }
}

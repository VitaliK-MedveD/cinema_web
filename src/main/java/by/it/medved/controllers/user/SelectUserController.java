package by.it.medved.controllers.user;

import by.it.medved.entities.User;
import by.it.medved.services.UserService;
import by.it.medved.services.UserServiceImpl;
import by.it.medved.util.Link;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/select")
public class SelectUserController extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        String updateAccess = req.getParameter("updateAccess");
        if (updateAccess != null) {
            User user = userService.getById(userId);
            req.setAttribute("userUpdateAccess", user);
            req.getRequestDispatcher(Link.USER_UPDATE_ACCESS_PAGE).forward(req, resp);
        } else {
            userService.deleteById(userId);
            req.getRequestDispatcher(Link.USERS_READ_URI).forward(req, resp);
        }
    }
}

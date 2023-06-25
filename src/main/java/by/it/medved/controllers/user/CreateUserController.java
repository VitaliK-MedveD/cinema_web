package by.it.medved.controllers.user;

import by.it.medved.entities.User;
import by.it.medved.mappers.Mapper;
import by.it.medved.services.UserService;
import by.it.medved.services.UserServiceImpl;
import by.it.medved.util.Link;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/create")
public class CreateUserController extends HttpServlet {

    private final UserService userService = new UserServiceImpl();
    private final Mapper userMapper = new Mapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userMapper.buildUser(
                req.getParameter("login"),
                req.getParameter("password"),
                req.getParameter("firstName"),
                req.getParameter("email"),
                req.getParameter("dateBirthday"));
        userService.createUser(user);
        req.getRequestDispatcher(Link.USERS_READ_URI).forward(req, resp);
    }
}

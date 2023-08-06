package by.it.medved.controllers.user;

import by.it.medved.entities.User;
import by.it.medved.mappers.Mapper;
import by.it.medved.services.UserService;
import org.hibernate.Hibernate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.it.medved.mappers.Mapper.getMapper;
import static by.it.medved.services.UserServiceImpl.getUserService;
import static by.it.medved.util.Link.*;
import static by.it.medved.util.FieldsEntities.*;

@WebServlet(urlPatterns = "/user/create")
public class CreateUserController extends HttpServlet {

    private final UserService userService = getUserService();
    private final Mapper userMapper = getMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User createdUser = userMapper.buildUser(
                req.getParameter(LOGIN),
                req.getParameter(PASSWORD),
                req.getParameter(FIRST_NAME),
                req.getParameter(EMAIL),
                req.getParameter(DATE_BIRTHDAY));
        User user = userService.createUser(createdUser);
        HttpSession session = req.getSession();
        session.setAttribute(USER, user);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(USER_MENU_PAGE).forward(req, resp);
    }
}

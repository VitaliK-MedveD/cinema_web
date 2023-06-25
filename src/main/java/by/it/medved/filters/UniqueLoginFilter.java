package by.it.medved.filters;

import by.it.medved.entities.User;
import by.it.medved.services.UserService;
import by.it.medved.services.UserServiceImpl;
import by.it.medved.util.Link;
import by.it.medved.util.Message;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebFilter(urlPatterns = "/user/create")
public class UniqueLoginFilter extends HttpFilter {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String login = req.getParameter("login");
        List<User> users = userService.getAllUsers();
        if (users.stream()
                .noneMatch(user -> user.getLogin().equalsIgnoreCase(login))) {
            chain.doFilter(req, res);
        } else {
            String loginBusyMessage = login + Message.LOGIN_BUSY_MESSAGE;
            req.setAttribute("loginBusyMessage", loginBusyMessage);
            req.getRequestDispatcher(Link.USER_REGISTRATION_PAGE).forward(req, res);
        }
    }
}

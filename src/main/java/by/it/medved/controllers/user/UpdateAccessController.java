package by.it.medved.controllers.user;

import by.it.medved.entities.Access;
import by.it.medved.entities.User;
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

@WebServlet(urlPatterns = "/user/update/access")
public class UpdateAccessController extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getAccess().equals(Access.ADMIN)) {

        }
        User selectedUser = (User) session.getAttribute("selectedUser");
        String access = req.getParameter("access");
        userService.updateAccess(selectedUser.getId(), Access.valueOf(access));
        req.getRequestDispatcher(Link.USERS_READ_URI).forward(req, resp);
    }
}

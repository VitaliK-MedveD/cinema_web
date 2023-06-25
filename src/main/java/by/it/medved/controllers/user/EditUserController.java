package by.it.medved.controllers.user;

import by.it.medved.entities.User;
import by.it.medved.services.UserService;
import by.it.medved.services.UserServiceImpl;
import by.it.medved.util.Link;
import by.it.medved.util.Regex;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/user/edit")
public class EditUserController extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String firstName = req.getParameter("firstName");
        String email = req.getParameter("email");
        String dateBirthday = req.getParameter("dateBirthday");
        user = userService.updateUserFields(user.getId(), firstName, email, dateBirthday);
        session.setAttribute("user", user);
        String updateProfileSuccessful = "Update successful";
        session.setAttribute("updateProfileSuccessful", updateProfileSuccessful);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Link.USER_EDIT_PAGE).forward(req, resp);
    }
}

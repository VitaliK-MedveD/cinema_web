package by.it.medved.filters;

import by.it.medved.dto.UserFieldsValidationDto;
import by.it.medved.services.FiltrationService;
import by.it.medved.services.FiltrationServiceImpl;
//import static by.it.medved.util.Link.*;
import by.it.medved.services.UserService;
import by.it.medved.services.UserServiceImpl;
import by.it.medved.util.Link;
import by.it.medved.util.Message;
import by.it.medved.util.Regex;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.it.medved.util.Link.USER_EDIT_PAGE;

@WebFilter(urlPatterns = "/user/edit")
public class UserEditFieldsFilter extends HttpFilter {

    private final FiltrationService filtrationService = new FiltrationServiceImpl();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String firstName = req.getParameter("firstName");
        String email = req.getParameter("email");
        String dateBirthday = req.getParameter("dateBirthday");
        if (filtrationService.checkUserFields(firstName, email, dateBirthday)) {
            chain.doFilter(req, res);
        } else {
            session.setAttribute("editUser", filtrationService.getUserFieldsValidationDto());
            req.getRequestDispatcher(USER_EDIT_PAGE).forward(req, res);
        }
    }
}

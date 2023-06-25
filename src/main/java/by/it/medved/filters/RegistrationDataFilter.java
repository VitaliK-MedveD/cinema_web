package by.it.medved.filters;

import by.it.medved.services.FiltrationService;
import by.it.medved.services.FiltrationServiceImpl;
import by.it.medved.util.Link;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/user/create")
public class RegistrationDataFilter extends HttpFilter {

    private final FiltrationService filtrationService = new FiltrationServiceImpl();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String email = req.getParameter("email");
        String dateBirthday = req.getParameter("dateBirthday");
        if (filtrationService.checkUserFields(login, password, firstName, email, dateBirthday)) {
            chain.doFilter(req, res);
        } else {
            session.setAttribute("verifiedUser", filtrationService.getUserFieldsValidationDto());
            req.getRequestDispatcher(Link.USER_REGISTRATION_PAGE).forward(req, res);
        }
    }
}

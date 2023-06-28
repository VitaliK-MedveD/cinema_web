package by.it.medved.filters;

import by.it.medved.services.FiltrationService;
import by.it.medved.services.FiltrationServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.it.medved.util.Link.*;
import static by.it.medved.util.FieldsEntities.*;

@WebFilter(urlPatterns = "/user/create")
public class RegistrationDataFilter extends HttpFilter {

    private final FiltrationService filtrationService = new FiltrationServiceImpl();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res,
                            FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        String firstName = req.getParameter(FIRST_NAME);
        String email = req.getParameter(EMAIL);
        String dateBirthday = req.getParameter(DATE_BIRTHDAY);
        if (filtrationService.checkUserFields(login, password, firstName, email, dateBirthday)) {
            chain.doFilter(req, res);
        } else {
            session.setAttribute(VERIFIED_USER, filtrationService.getUserFieldsValidationDto());
            req.getRequestDispatcher(USER_REGISTRATION_PAGE).forward(req, res);
        }
    }
}

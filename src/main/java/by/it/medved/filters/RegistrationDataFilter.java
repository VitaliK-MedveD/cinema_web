package by.it.medved.filters;

import by.it.medved.services.FiltrationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.it.medved.services.FiltrationServiceImpl.getFiltrationService;
import static by.it.medved.util.FieldsEntities.*;
import static by.it.medved.util.Link.*;

@WebFilter(urlPatterns = "/user/create")
public class RegistrationDataFilter extends HttpFilter {

    private final FiltrationService filtrationService = getFiltrationService();

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
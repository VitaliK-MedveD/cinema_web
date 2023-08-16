package by.it.medved.filters;

import by.it.medved.entities.User;
import by.it.medved.services.EncryptionService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.it.medved.services.EncryptionServiceImpl.getEncryptionService;
import static by.it.medved.util.FieldsEntities.*;
import static by.it.medved.util.Link.CHANGE_PASSWORD_PAGE;
import static by.it.medved.util.Message.INCORRECT_CURRENT_PASSWORD;
import static by.it.medved.util.Message.PASSWORDS_MISMATCH;

@WebFilter(urlPatterns = "/user/change_password")
public class ChangePasswordFilter extends HttpFilter {

    private final EncryptionService encryptionService = getEncryptionService();
    private String errorMessage;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);
        String currentPassword = req.getParameter(CURRENT_PASSWORD);
        String newPassword = req.getParameter(NEW_PASSWORD);
        String repeatNewPassword = req.getParameter(REPEAT_NEW_PASSWORD);
        if (checkCurrentPassword(currentPassword, user) && checkPasswordsMatch(newPassword, repeatNewPassword)) {
            chain.doFilter(req, res);
        } else {
            req.setAttribute(ERROR_MESSAGE, errorMessage);
            req.getRequestDispatcher(CHANGE_PASSWORD_PAGE).forward(req, res);
        }
    }

    private boolean checkCurrentPassword(String currentPassword, User user) {
        if (encryptionService.authenticate(currentPassword, user.getPassword(), user.getSalt())) {
            return true;
        } else {
            errorMessage = INCORRECT_CURRENT_PASSWORD;
            return false;
        }
    }

    private boolean checkPasswordsMatch(String newPassword, String repeatNewPassword) {
        if (newPassword.equals(repeatNewPassword)) {
            return true;
        } else {
            errorMessage = PASSWORDS_MISMATCH;
            return false;
        }
    }
}
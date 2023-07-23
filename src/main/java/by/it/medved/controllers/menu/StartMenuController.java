package by.it.medved.controllers.menu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.it.medved.util.FieldsEntities.*;
import static by.it.medved.util.Link.*;

@WebServlet(urlPatterns = "/start")
public class StartMenuController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter(ACTION)) {
            case SING_IN :
                req.getRequestDispatcher(USER_SING_IN_PAGE).forward(req, resp);
                break;
            case REGISTER :
                req.getRequestDispatcher(USER_REGISTRATION_PAGE).forward(req, resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
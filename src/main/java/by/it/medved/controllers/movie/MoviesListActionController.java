package by.it.medved.controllers.movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.it.medved.util.Link.*;
import static by.it.medved.util.FieldsEntities.*;

@WebServlet(urlPatterns = "/movie/action")
public class MoviesListActionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter(ACTION)) {
            case BUY_TICKET :
                req.getRequestDispatcher(TICKET_BUY_URI).forward(req, resp);
                break;
            case EDIT_MOVIE :
                req.getRequestDispatcher(MOVIE_EDIT_URI).forward(req, resp);
                break;
            case DELETE_MOVIE :
                req.getRequestDispatcher(MOVIE_DELETE_URI).forward(req, resp);
                break;
        }
    }
}

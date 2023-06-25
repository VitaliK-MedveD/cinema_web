package by.it.medved.controllers.movie;

import by.it.medved.util.Link;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/movie/action")
public class MoviesListActionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("action")) {
            case "buyTicket" :
                req.getRequestDispatcher("/ticket/buy").forward(req, resp);
                break;
            case "editMovie" :
                req.getRequestDispatcher("/movie/edit").forward(req, resp);
                break;
            case "deleteMovie" :
                req.getRequestDispatcher("/movie/delete").forward(req, resp);
                break;
        }
    }
}

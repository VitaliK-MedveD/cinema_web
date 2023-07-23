package by.it.medved.controllers.movie;

import by.it.medved.entities.Role;
import by.it.medved.entities.Movie;
import by.it.medved.entities.User;
import by.it.medved.services.MovieService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.it.medved.services.MovieServiceImpl.getMovieService;
import static by.it.medved.util.Link.*;
import static by.it.medved.util.FieldsEntities.*;

@WebServlet(urlPatterns = "/movie/delete")
public class DeleteMovieController extends HttpServlet {

    private final MovieService movieService = getMovieService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Movie selectedMovie = (Movie) session.getAttribute(SELECTED_MOVIE);
        movieService.deleteMovie(selectedMovie);
        req.getRequestDispatcher(ADMIN_MENU_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);
        if (user.getRole().equals(Role.ADMIN)) {
            doGet(req, resp);
        } else {
            req.getRequestDispatcher(FORBIDDEN_403_PAGE).forward(req, resp);
        }
    }
}

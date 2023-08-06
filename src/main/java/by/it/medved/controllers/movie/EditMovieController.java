package by.it.medved.controllers.movie;

import by.it.medved.entities.Movie;
import by.it.medved.services.MovieService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.it.medved.services.MovieServiceImpl.getMovieService;
import static by.it.medved.util.FieldsEntities.*;
import static by.it.medved.util.Link.MOVIE_EDIT_PAGE;
import static by.it.medved.util.Message.UPDATE_SUCCESSFUL;

@WebServlet(urlPatterns = "/movie/edit")
public class EditMovieController extends HttpServlet {

    private final MovieService movieService = getMovieService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Movie selectedMovie = (Movie) session.getAttribute(SELECTED_MOVIE);
        String showDateTime = req.getParameter(SHOW_DATE_TIME);
        String price = req.getParameter(PRICE);
        selectedMovie = movieService.updateMovie(selectedMovie.getId(), showDateTime, price);
        session.setAttribute(SELECTED_MOVIE, selectedMovie);
        req.setAttribute(UPDATE_MOVIE_SUCCESSFUL, UPDATE_SUCCESSFUL);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(MOVIE_EDIT_PAGE).forward(req, resp);
    }
}

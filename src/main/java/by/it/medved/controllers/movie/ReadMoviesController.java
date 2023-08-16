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
import java.util.List;

import static by.it.medved.services.MovieServiceImpl.getMovieService;
import static by.it.medved.util.FieldsEntities.MOVIES;
import static by.it.medved.util.Link.MOVIES_PAGE;

@WebServlet(urlPatterns = "/movies/read")
public class ReadMoviesController extends HttpServlet {

    private final MovieService movieService = getMovieService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Movie> movies = movieService.getMovies();
        session.setAttribute(MOVIES, movies);
        req.getRequestDispatcher(MOVIES_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

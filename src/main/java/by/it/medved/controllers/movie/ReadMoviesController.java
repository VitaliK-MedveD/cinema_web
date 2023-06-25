package by.it.medved.controllers.movie;

import by.it.medved.entities.Movie;
import by.it.medved.entities.User;
import by.it.medved.services.MovieService;
import by.it.medved.services.MovieServiceImpl;
import by.it.medved.util.Link;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/movie/read")
public class ReadMoviesController extends HttpServlet {

    private final MovieService movieService = new MovieServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Movie> movies = movieService.getAllMovies();
        session.setAttribute("movies", movies);
        req.getRequestDispatcher("/pages/movie/read-movies.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

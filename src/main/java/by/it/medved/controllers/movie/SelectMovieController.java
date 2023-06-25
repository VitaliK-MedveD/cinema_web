package by.it.medved.controllers.movie;

import by.it.medved.entities.Movie;
import by.it.medved.entities.User;
import by.it.medved.services.MovieService;
import by.it.medved.services.MovieServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/movie/select")
public class SelectMovieController extends HttpServlet {

    private final MovieService movieService = new MovieServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long movieId = Long.parseLong(req.getParameter("movieId"));
        Movie selectedMovie = movieService.getMovieById(movieId);
        session.setAttribute("selectedMovie", selectedMovie);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

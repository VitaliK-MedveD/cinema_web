package by.it.medved.controllers.movie;

import by.it.medved.entities.Movie;
import by.it.medved.mappers.Mapper;
import by.it.medved.services.MovieService;
import by.it.medved.services.MovieServiceImpl;
import by.it.medved.util.Link;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/movie/create")
public class CreateMovieController extends HttpServlet {

    private final MovieService movieService = new MovieServiceImpl();
    private final Mapper movieMapper = new Mapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Movie movie = movieMapper.buildMovie(
                req.getParameter("movieTitle"),
                req.getParameter("showDate"),
                req.getParameter("showTime"),
                req.getParameter("price"),
                req.getParameter("ageLimit"));
        movieService.createMovie(movie);
        req.getRequestDispatcher(Link.ADMIN_MENU_PAGE).forward(req, resp);
//        doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Link.ADMIN_MENU_PAGE).forward(req, resp);
    }
}

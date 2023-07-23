package by.it.medved.controllers.movie;

import by.it.medved.entities.Movie;
import by.it.medved.mappers.Mapper;
import by.it.medved.services.MovieService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.it.medved.mappers.Mapper.getMapper;
import static by.it.medved.services.MovieServiceImpl.getMovieService;
import static by.it.medved.util.Link.*;
import static by.it.medved.util.FieldsEntities.*;

@WebServlet(urlPatterns = "/movie/create")
public class AddMovieController extends HttpServlet {

    private final MovieService movieService = getMovieService();
    private final Mapper movieMapper = getMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Movie movie = movieMapper.buildMovie(
                req.getParameter(MOVIE_TITLE),
                req.getParameter(SHOW_DATE),
                req.getParameter(SHOW_TIME),
                req.getParameter(PRICE),
                req.getParameter(AGE_LIMIT));
        movieService.addMovie(movie);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ADMIN_MENU_PAGE).forward(req, resp);
    }
}

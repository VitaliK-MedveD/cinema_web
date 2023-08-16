package by.it.medved.controllers.movie;

import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.services.MovieService;
import by.it.medved.services.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static by.it.medved.services.MovieServiceImpl.getMovieService;
import static by.it.medved.services.TicketServiceImpl.getTicketService;
import static by.it.medved.util.Link.*;
import static by.it.medved.util.FieldsEntities.*;

@WebServlet(urlPatterns = "/movie/select")
public class SelectMovieController extends HttpServlet {

    private final MovieService movieService = getMovieService();
    private final TicketService ticketService = getTicketService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long movieId = Long.parseLong(req.getParameter(MOVIE_ID));
        Movie selectedMovie = movieService.getMovieById(movieId);
        List<Ticket> movieTickets = ticketService.getMovieTickets(movieId);
        session.setAttribute(SELECTED_MOVIE, selectedMovie);
        session.setAttribute(MOVIE_TICKETS, movieTickets);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(MOVIE_ACTION_PAGE).forward(req, resp);
    }
}

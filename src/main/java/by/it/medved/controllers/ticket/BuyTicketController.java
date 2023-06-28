package by.it.medved.controllers.ticket;

import by.it.medved.entities.Movie;
import by.it.medved.entities.User;
import by.it.medved.services.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.it.medved.services.TicketServiceImpl.getTicketService;
import static by.it.medved.util.Link.*;
import static by.it.medved.util.FieldsEntities.*;

@WebServlet(urlPatterns = "/ticket/buy")
public class BuyTicketController extends HttpServlet {

    private static final String BUY_SUCCESSFULLY = "Ticket buy successfully";
    private static final String NOT_BUY = "Ticket was not buy.";
    private final TicketService ticketService = getTicketService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);
        Movie selectedMovie = (Movie) session.getAttribute(SELECTED_MOVIE);
        String message;
        if (ticketService.buyTicket(user, selectedMovie)) {
            message = BUY_SUCCESSFULLY;
            session.setAttribute(MESSAGE, message);
            doGet(req, resp);
        } else {
            message = NOT_BUY;
            session.setAttribute(MESSAGE, message);
            doGet(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(MOVIE_ACTION_PAGE).forward(req, resp);
    }
}

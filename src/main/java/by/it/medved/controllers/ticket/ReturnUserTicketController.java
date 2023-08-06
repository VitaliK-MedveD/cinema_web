package by.it.medved.controllers.ticket;

import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;
import by.it.medved.services.TicketService;
import by.it.medved.util.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.it.medved.services.TicketServiceImpl.getTicketService;
import static by.it.medved.util.FieldsEntities.*;
import static by.it.medved.util.FieldsEntities.ERROR_MESSAGE;
import static by.it.medved.util.Link.AUTHORIZATION_CONTROLLER_URI;

@WebServlet(urlPatterns = "/ticket/return_userTicket")
public class ReturnUserTicketController extends HttpServlet {

    private final TicketService ticketService = getTicketService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long ticketId = Long.parseLong(req.getParameter(TICKET_ID));
        User selectedUser = (User) session.getAttribute(SELECTED_USER);
        Ticket ticket = ticketService.getTicketById(ticketId);
        String message;
        if (ticketService.returnTicket(ticket)) {
            message = Message.RETURN_SUCCESSFULLY;
            selectedUser.removeTicket(ticket);
            session.setAttribute(SELECTED_USER, selectedUser);
            session.setAttribute(MESSAGE, message);
            doGet(req, resp);
        } else {
            message = ticketService.getErrorMessage();
            session.setAttribute(ERROR_MESSAGE, message);
            doGet(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(AUTHORIZATION_CONTROLLER_URI).forward(req, resp);
    }
}

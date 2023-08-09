package by.it.medved.controllers.ticket;

import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;
import by.it.medved.services.TicketService;
import org.apache.commons.collections.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.it.medved.services.TicketServiceImpl.getTicketService;
import static by.it.medved.util.FieldsEntities.*;
import static by.it.medved.util.Link.EDIT_USER_MENU_PAGE;
import static by.it.medved.util.Link.USER_TICKETS_PAGE;
import static by.it.medved.util.Message.USER_TICKETS_EMPTY;

@WebServlet(urlPatterns = "/ticket/read_userTickets")
public class ReadUserTicketsController extends HttpServlet {

    private final TicketService ticketService = getTicketService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User selectedUser = (User) session.getAttribute(SELECTED_USER);
        List<Ticket> userTickets = ticketService.getUserTickets(selectedUser.getId());
        if (CollectionUtils.isNotEmpty(userTickets)) {
            req.setAttribute(USER_TICKETS, userTickets);
            req.getRequestDispatcher(USER_TICKETS_PAGE).forward(req, resp);
        } else {
            req.setAttribute(NO_TICKETS, USER_TICKETS_EMPTY);
            req.getRequestDispatcher(EDIT_USER_MENU_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

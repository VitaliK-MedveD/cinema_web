package by.it.medved.services;

import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;

import java.util.List;

public interface TicketService {

    boolean createTenTickets(Movie movie);
    List<Ticket> getAllTickets(Long id, String columnName);
    boolean updateTicket(Ticket ticket);
    boolean deleteTicket(Ticket ticket);
}

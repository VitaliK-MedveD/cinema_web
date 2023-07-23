package by.it.medved.services;

import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;

import java.util.List;

public interface TicketService {

    boolean addTenTickets(Movie movie);

    boolean buyTicket(User user, Movie movie);

    String getErrorMessage();

    List<Ticket> getAllTickets(Long id, String columnName);

    boolean updateTicket(Ticket ticket);

    boolean deleteTicket(Ticket ticket);
}

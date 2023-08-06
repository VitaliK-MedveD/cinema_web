package by.it.medved.services;

import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;

import java.util.List;

public interface TicketService {

    List<Ticket> addTenTickets(Movie movie);

    Ticket getTicketById(Long id);

    boolean buyTicket(User user, Movie movie);

    boolean returnTicket(Ticket ticket);

    String getErrorMessage();

    Ticket getTicket();
}
package by.it.medved.services;

import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {

    List<Ticket> addTenTickets(Movie movie);

    Ticket getTicketById(Long id);

    List<Ticket> getUserTickets(Long userId);

    List<Ticket> getMovieTickets(Long movieId);

    void updateMovieTickets(Long movieId, LocalDateTime showDateTime, BigDecimal price);

    int getCountFreeTickets (Long movieId);

    boolean buyTicket(User user, Movie movie);

    boolean returnTicket(Ticket ticket);

    void returnUserTickets(Long userId);

    String getErrorMessage();

    Ticket getTicket();
}
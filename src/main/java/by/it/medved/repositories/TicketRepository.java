package by.it.medved.repositories;

import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository {

    Ticket createTicket(Ticket ticket);

    Ticket getTicketById(Long id);

    List<Ticket> getUserTickets(Long userId);

    List<Ticket> getMovieTickets(Long movieId);

    void updateMovieTicket(Long ticketId, LocalDateTime showDateTime, BigDecimal price);

    int getCountFreeTickets (Long movieId);

    boolean buyTicket(Long ticketId, User user);

    boolean returnTicket(Long ticketId);
}

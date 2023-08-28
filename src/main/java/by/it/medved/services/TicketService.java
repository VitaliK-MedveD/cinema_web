package by.it.medved.services;

import by.it.medved.dto.TicketResponse;
import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {

    List<Ticket> addTenTickets(Movie movie);

    TicketResponse getTicketById(Long id);

    List<TicketResponse> getUserTickets(Long userId);

    List<TicketResponse> getMovieTickets(Long movieId);

    void updateMovieTickets(Long movieId, LocalDateTime showDateTime, BigDecimal price);

    int getCountFreeTickets (Long movieId);
}
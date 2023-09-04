package by.it.medved.services;

import by.it.medved.dto.response.TicketResponse;
import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> addTenTickets(Movie movie);

    TicketResponse getTicketById(Long id);

    List<TicketResponse> getUserTickets(Long userId);

    List<TicketResponse> getMovieTickets(Long movieId);

    Integer getFreeTicketsCount(Long movieId);
}
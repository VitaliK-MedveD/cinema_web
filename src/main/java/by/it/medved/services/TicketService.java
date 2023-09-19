package by.it.medved.services;

import by.it.medved.dto.request.BuyTicketsRequest;
import by.it.medved.dto.response.TicketResponse;
import by.it.medved.entities.User;

import java.util.List;

public interface TicketService {

    List<TicketResponse> buyTickets(User user, BuyTicketsRequest buyTicketsRequest);

    TicketResponse getTicketById(Long id);

    List<TicketResponse> getUserTickets(Long userId);

    List<TicketResponse> getMovieTickets(Long movieId);

    void returnTicket(Long id);

    void returnUserTickets(Long userId);

    Integer getFreeTicketsCount(Long movieId);
}
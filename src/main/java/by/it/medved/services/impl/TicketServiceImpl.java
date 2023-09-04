package by.it.medved.services.impl;

import by.it.medved.dto.response.TicketResponse;
import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.mappers.TicketMapper;
import by.it.medved.repositories.TicketRepository;
import by.it.medved.services.TicketService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static by.it.medved.util.Message.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public List<Ticket> addTenTickets(Movie movie) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Ticket ticket = Ticket.builder()
                    .movie(movie)
                    .placeNumber(i + 1)
                    .build();
            tickets.add(ticket);
        }
        return tickets;
    }

    @Override
    public TicketResponse getTicketById(Long id) {
        return ticketRepository.findById(id)
                .map(ticketMapper::mapToTicketResponse)
                .orElseThrow(() -> new EntityNotFoundException(format(TICKET_BY_ID_NOT_EXIST, id)));
    }

    @Override
    public List<TicketResponse> getUserTickets(Long userId) {
        return ticketRepository.findTicketsByUserId(userId)
                .stream()
                .map(ticketMapper::mapToTicketResponse)
                .toList();
    }

    @Override
    public List<TicketResponse> getMovieTickets(Long movieId) {
        return ticketRepository.findTicketsByMovieId(movieId)
                .stream()
                .map(ticketMapper::mapToTicketResponse)
                .toList();
    }

    @Override
    public Integer getFreeTicketsCount(Long movieId) {
        return ticketRepository.findTicketsByMovieIdAndUserNull(movieId)
                .size();
    }
}
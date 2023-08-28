package by.it.medved.services.impl;

import by.it.medved.dto.TicketResponse;
import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;
import by.it.medved.mappers.TicketMapper;
import by.it.medved.repositories.TicketRepository;
import by.it.medved.services.TicketService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
                    .movieTitle(movie.getMovieTitle())
                    .showDateTime(movie.getShowDateTime())
                    .numberOfPlace(i + 1)
                    .price(movie.getPrice())
                    .build();
            tickets.add(ticket);
        }
        return tickets;
    }

    @Override
    public TicketResponse getTicketById(Long id) {
        return ticketRepository.findById(id)
                .map(ticketMapper::buildTicketResponse)
                .orElseThrow(() -> new EntityNotFoundException("Ticket with id '" + id + "' does not exist"));
    }

    @Override
    public List<TicketResponse> getUserTickets(Long userId) {
        return ticketRepository.findTicketsByUserId(userId)
                .stream()
                .map(ticketMapper::buildTicketResponse)
                .toList();
    }

    @Override
    public List<TicketResponse> getMovieTickets(Long movieId) {
        return ticketRepository.findTicketsByMovieId(movieId)
                .stream()
                .map(ticketMapper::buildTicketResponse)
                .toList();
    }

    @Override
    public void updateMovieTickets(Long movieId, LocalDateTime showDateTime, BigDecimal price) {
        ticketRepository.findTicketsByMovieId(movieId).stream()
                .forEach(ticket -> {
                    ticket.setShowDateTime(showDateTime);
                    ticket.setPrice(price);
                    ticketRepository.save(ticket);
                });
    }

    @Override
    public int getCountFreeTickets(Long movieId) {
        return (int) ticketRepository.findTicketsByMovieId(movieId).stream()
                .filter(ticket -> ticket.getUser() == null)
                .count();
    }
}
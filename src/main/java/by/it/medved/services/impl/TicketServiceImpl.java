package by.it.medved.services.impl;

import by.it.medved.dto.request.BuyTicketsRequest;
import by.it.medved.dto.response.TicketResponse;
import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;
import by.it.medved.mappers.TicketMapper;
import by.it.medved.repositories.TicketRepository;
import by.it.medved.services.TicketService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.it.medved.util.Message.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    @Transactional
    public List<TicketResponse> buyTickets(User user, BuyTicketsRequest buyTicketsRequest) {
        List<Long> ticketsId = buyTicketsRequest.getTicketsId();
        return ticketsId.stream()
                .map(id -> buyTicket(user, id))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TicketResponse getTicketById(Long id) {
        return ticketRepository.findById(id)
                .map(ticketMapper::mapToTicketResponse)
                .orElseThrow(() -> new EntityNotFoundException(format(TICKET_BY_ID_NOT_EXIST, id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketResponse> getUserTickets(Long userId) {
        return ticketRepository.findTicketsByUserId(userId)
                .stream()
                .map(ticketMapper::mapToTicketResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketResponse> getMovieTickets(Long movieId) {
        return ticketRepository.findTicketsByMovieId(movieId)
                .stream()
                .map(ticketMapper::mapToTicketResponse)
                .toList();
    }

    @Override
    @Transactional
    public void returnTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format(TICKET_BY_ID_NOT_EXIST, id)));
        returnTicket(ticket);
    }

    @Override
    @Transactional
    public void returnUserTickets(Long userId) {
        ticketRepository.findTicketsByUserId(userId)
                .forEach(this::returnTicket);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getFreeTicketsCount(Long movieId) {
        return ticketRepository.findTicketsByMovieIdAndUserNull(movieId)
                .size();
    }

    private TicketResponse buyTicket(User user, Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new EntityNotFoundException(format(TICKET_BY_ID_NOT_EXIST, ticketId)));
        ticket.setUser(user);
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.mapToTicketResponse(savedTicket);
    }

    private void returnTicket (Ticket ticket) {
        ticket.setUser(null);
        ticketRepository.save(ticket);
    }
}
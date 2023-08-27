package by.it.medved.controllers;

import by.it.medved.dto.TicketResponse;
import by.it.medved.entities.Ticket;
import by.it.medved.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/ticketById/{id}")
    public TicketResponse getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping("/ticketsByUser/{userId}")
    public List<Ticket> getUserTickets(@PathVariable Long userId) {
        return ticketService.getUserTickets(userId);
    }

    @GetMapping("/ticketsByMovie/{movieId}")
    public List<Ticket> getMovieTickets(@PathVariable Long movieId) {
        return ticketService.getMovieTickets(movieId);
    }
}
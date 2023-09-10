package by.it.medved.controllers;

import by.it.medved.dto.request.BuyTicketsRequest;
import by.it.medved.dto.response.TicketResponse;
import by.it.medved.entities.User;
import by.it.medved.services.TicketService;
import by.it.medved.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;

    @PatchMapping("/ticket/{id}")
    public List<TicketResponse> buyTickets(@PathVariable Long id, @Valid @RequestBody BuyTicketsRequest buyTicketRequest) {
        User user = userService.getUser(id);
        return ticketService.buyTickets(user, buyTicketRequest);
    }

    @GetMapping("/ticket/{id}")
    public TicketResponse getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping("/ticketsByUser/{userId}")
    public List<TicketResponse> getUserTickets(@PathVariable Long userId) {
        return ticketService.getUserTickets(userId);
    }

    @GetMapping("/ticketsByMovie/{movieId}")
    public List<TicketResponse> getMovieTickets(@PathVariable Long movieId) {
        return ticketService.getMovieTickets(movieId);
    }

    @PatchMapping("/returnTicket/{ticketId}")
    public void returnTicket(@PathVariable Long ticketId) {
        ticketService.returnTicket(ticketId);
    }

    @PatchMapping("/returnTickets/{userId}")
    public void returnUserTickets(@PathVariable Long userId) {
        ticketService.returnUserTickets(userId);
    }
}
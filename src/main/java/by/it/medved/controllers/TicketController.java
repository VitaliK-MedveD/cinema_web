package by.it.medved.controllers;

import by.it.medved.dto.request.BuyTicketsRequest;
import by.it.medved.dto.response.TicketResponse;
import by.it.medved.entities.User;
import by.it.medved.services.TicketService;
import by.it.medved.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Tag(name = "Ticket Controller", description = "Responsible for handling queries and managing ticket-related activities")
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;

    @PatchMapping("/ticket/{id}")
    @Operation(description = "Buying tickets")
    public List<TicketResponse> buyTickets(@PathVariable Long id, @Valid @RequestBody BuyTicketsRequest buyTicketRequest) {
        User user = userService.getUser(id);
        return ticketService.buyTickets(user, buyTicketRequest);
    }

    @GetMapping("/ticket/{id}")
    @Operation(description = "Getting ticket by ID")
    public TicketResponse getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping("/ticketsByUser/{userId}")
    @Operation(description = "Getting all user tickets")
    public List<TicketResponse> getUserTickets(@PathVariable Long userId) {
        return ticketService.getUserTickets(userId);
    }

    @GetMapping("/ticketsByMovie/{movieId}")
    @Operation(description = "Getting all movie tickets")
    public List<TicketResponse> getMovieTickets(@PathVariable Long movieId) {
        return ticketService.getMovieTickets(movieId);
    }

    @PatchMapping("/returnTicket/{ticketId}")
    @Operation(description = "Return ticket by ID")
    public void returnTicket(@PathVariable Long ticketId) {
        ticketService.returnTicket(ticketId);
    }

    @PatchMapping("/returnTickets/{userId}")
    @Operation(description = "Return all user tickets")
    public void returnUserTickets(@PathVariable Long userId) {
        ticketService.returnUserTickets(userId);
    }
}
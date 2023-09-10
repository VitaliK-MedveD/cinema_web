package by.it.medved.mappers;

import by.it.medved.dto.response.TicketResponse;
import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TicketMapper {

    public TicketResponse mapToTicketResponse(Ticket ticket) {
        Long userId = Optional.ofNullable(ticket.getUser())
                .map(User::getId)
                .orElse(null);
        return TicketResponse.builder()
                .id(ticket.getId())
                .movieId(ticket.getMovie().getId())
                .userId(userId)
                .placeNumber(ticket.getPlaceNumber())
                .build();
    }
}
package by.it.medved.mappers;

import by.it.medved.dto.TicketResponse;
import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TicketMapper {

    public TicketResponse buildTicketResponse(Ticket ticket) {
        Long userId = Optional.ofNullable(ticket.getUser())
                .map(user -> user.getId())
                .orElse(null);
        return TicketResponse.builder()
                .id(ticket.getId())
                .movieTitle(ticket.getMovieTitle())
                .showDateTime(ticket.getShowDateTime())
                .numberOfPlace(ticket.getNumberOfPlace())
                .price(ticket.getPrice())
                .userId(userId)
                .build();
    }
}
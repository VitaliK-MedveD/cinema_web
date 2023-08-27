package by.it.medved.mappers;

import by.it.medved.dto.TicketResponse;
import by.it.medved.entities.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketResponse buildTicketResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .movieTitle(ticket.getMovieTitle())
                .showDateTime(ticket.getShowDateTime())
                .numberOfPlace(ticket.getNumberOfPlace())
                .price(ticket.getPrice())
                .build();
    }
}
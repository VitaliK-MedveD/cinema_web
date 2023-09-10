package by.it.medved.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketResponse {

    private Long id;
    private Long movieId;
    private Long userId;
    private Integer placeNumber;
}
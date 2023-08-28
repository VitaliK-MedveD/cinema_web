package by.it.medved.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TicketResponse {

    private Long id;
    private String movieTitle;
    private LocalDateTime showDateTime;
    private int numberOfPlace;
    private BigDecimal price;
    private Long userId;
}
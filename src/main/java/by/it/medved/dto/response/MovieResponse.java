package by.it.medved.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class MovieResponse {

    private Long id;
    private String movieTitle;
    private LocalDateTime showDateTime;
    private BigDecimal price;
    private Integer ageLimit;
    private Integer freeTicketsCount;
}
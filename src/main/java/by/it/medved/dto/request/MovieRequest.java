package by.it.medved.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class MovieRequest {

    @NotBlank
    private String movieTitle;
    @Future
    private LocalDateTime showDateTime;
    @Positive
    private BigDecimal price;
    @PositiveOrZero
    private Integer ageLimit;
}
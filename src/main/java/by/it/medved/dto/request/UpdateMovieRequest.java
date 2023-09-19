package by.it.medved.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static by.it.medved.util.Message.*;

@Data
@Builder
public class UpdateMovieRequest {

    @Schema(example = EXAMPLE_TITLE)
    private String movieTitle;
    @Future
    @Schema(example = EXAMPLE_DATE_TIME)
    private LocalDateTime showDateTime;
    @Positive
    private BigDecimal price;
    @PositiveOrZero
    private Integer ageLimit;
}
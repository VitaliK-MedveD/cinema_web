package by.it.medved.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateMovieRequest {

    private Long id;

    @NotBlank
    private String showDateTime;

    @NotBlank
    private String price;
}
package by.it.medved.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieRequest {

    @NotBlank
    private String movieTitle;

    @Pattern(regexp = "")
    private String showDateTime;

    @NotBlank
    private String price;

    @NotBlank
    private String ageLimit;
}
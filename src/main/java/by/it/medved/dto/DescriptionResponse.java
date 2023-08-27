package by.it.medved.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DescriptionResponse {

    @NotBlank
    private String description;
}

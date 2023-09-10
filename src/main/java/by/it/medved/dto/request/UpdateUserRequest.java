package by.it.medved.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import static by.it.medved.util.Message.*;
import static by.it.medved.util.Patterns.*;

@Data
@Builder
public class UpdateUserRequest {

    @NotBlank
    private String firstName;
    @Pattern(regexp = EMAIL, message = EMAIL_REQUIREMENTS)
    private String email;
    @Past(message = DATE_REQUIREMENTS)
    private LocalDate dateBirthday;
}
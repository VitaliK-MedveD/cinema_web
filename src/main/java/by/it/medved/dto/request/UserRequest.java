package by.it.medved.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class UserRequest {

    @Schema(example = EXAMPLE_LOGIN)
    @Pattern(regexp = LOGIN, message = LOGIN_REQUIREMENTS)
    private String login;
    @Schema(example = EXAMPLE_PASSWORD)
    @Pattern(regexp = PASSWORD, message = PASSWORD_REQUIREMENTS)
    private String password;
    @NotBlank
    @Schema(example = EXAMPLE_LOGIN)
    private String firstName;
    @Schema(example = EXAMPLE_EMAIL)
    @Pattern(regexp = EMAIL, message = EMAIL_REQUIREMENTS)
    private String email;
    @Schema(example = EXAMPLE_DATE)
    @Past(message = DATE_REQUIREMENTS)
    private LocalDate dateBirthday;
}
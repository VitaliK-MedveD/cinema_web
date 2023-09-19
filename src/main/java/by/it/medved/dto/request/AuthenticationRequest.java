package by.it.medved.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import static by.it.medved.util.Message.*;
import static by.it.medved.util.Patterns.*;

@Data
@Builder
public class AuthenticationRequest {

    @Schema(example = EXAMPLE_LOGIN)
    @Pattern(regexp = LOGIN, message = LOGIN_REQUIREMENTS)
    private String login;
    @Schema(example = EXAMPLE_PASSWORD)
    @Pattern(regexp = PASSWORD, message = PASSWORD_REQUIREMENTS)
    private String password;
}
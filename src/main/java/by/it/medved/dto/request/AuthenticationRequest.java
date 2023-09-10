package by.it.medved.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import static by.it.medved.util.Message.*;
import static by.it.medved.util.Patterns.*;

@Data
@Builder
public class AuthenticationRequest {

    @Pattern(regexp = LOGIN, message = LOGIN_REQUIREMENTS)
    private String login;
    @Pattern(regexp = PASSWORD, message = PASSWORD_REQUIREMENTS)
    private String password;
}
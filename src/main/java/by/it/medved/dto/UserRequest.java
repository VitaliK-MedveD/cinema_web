package by.it.medved.dto;

import by.it.medved.util.Message;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import static by.it.medved.util.Message.*;
import static by.it.medved.util.Regex.*;

@Data
@Builder
public class UserRequest {

    @Pattern(regexp = LOGIN, message = REGEX_LOGIN)
    private String login;

    @Pattern(regexp = PASSWORD, message = REGEX_PASSWORD)
    private String password;

    @Pattern(regexp = FIRST_NAME, message = REGEX_FIRST_NAME)
    private String firstName;

    @Pattern(regexp = EMAIL, message = REGEX_EMAIL)
    private String email;

    @Pattern(regexp = DATE, message = REGEX_DATE)
    private String dateBirthday;
}
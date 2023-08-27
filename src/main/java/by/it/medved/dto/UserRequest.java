package by.it.medved.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import static by.it.medved.util.Regex.*;

@Data
@Builder
public class UserRequest {

    @Pattern(regexp = LOGIN, message = "Логин дожен быть нормальным!")
    private String login;

    @Pattern(regexp = PASSWORD, message = "Пароль дожен быть нормальным!")
    private String password;

    @Pattern(regexp = FIRST_NAME, message = "Имя дожно быть нормальным!")
    private String firstName;

    @Pattern(regexp = EMAIL, message = "Email дожен быть нормальным!")
    private String email;

    @Pattern(regexp = DATE, message = "Дата дожна быть нормальной!")
    private String dateBirthday;
}
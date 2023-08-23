package by.it.medved.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserRequest {

    private String login;
    private String password;
    private String firstName;
    private String email;
    private LocalDate dateBirthday;
}

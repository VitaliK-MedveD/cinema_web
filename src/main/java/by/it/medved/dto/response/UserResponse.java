package by.it.medved.dto.response;

import by.it.medved.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponse {

    private Long id;
    private Role role;
    private String login;
    private String firstName;
    private String email;
    private LocalDate dateBirthday;
    private LocalDate dateCreated;
}
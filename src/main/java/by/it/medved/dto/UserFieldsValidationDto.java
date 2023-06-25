package by.it.medved.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFieldsValidationDto {

    private String login;
    private String password;
    private String firstName;
    private String email;
    private String dateBirthday;
    private String notValidLogin;
    private String notValidPassword;
    private String notValidFirstName;
    private String notValidEmail;
    private String notValidDateBirthday;
}

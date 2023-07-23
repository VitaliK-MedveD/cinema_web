package by.it.medved.dto;

import lombok.Data;

@Data
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
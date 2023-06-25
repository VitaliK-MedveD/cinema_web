package by.it.medved.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private Access access;
    private String login;
    private byte[] password;
    private String firstName;
    private String email;
    private LocalDate dateBirthday;
    private LocalDate dateCreated;
    private byte[] salt;

}

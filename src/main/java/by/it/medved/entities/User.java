package by.it.medved.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PERSON")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ROLE")
    private Role role;

    @Column(unique = true, name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private byte[] password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DATE_BIRTHDAY")
    private LocalDate dateBirthday;

    @Column(name = "DATE_CREATED")
    private LocalDate dateCreated;

    @Column(name = "SALT")
    private byte[] salt;
}



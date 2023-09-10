package by.it.medved.entities;

import by.it.medved.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static by.it.medved.util.Columns.*;
import static jakarta.persistence.EnumType.STRING;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PERSON")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, updatable = false)
    private Long id;

    @Enumerated(STRING)
    @Column(name = ROLE, nullable = false)
    private Role role;

    @Column(name = LOGIN, unique = true, nullable = false, updatable = false)
    private String login;

    @Column(name = PASSWORD, nullable = false)
    private byte[] password;

    @Column(name = FIRST_NAME, nullable = false)
    private String firstName;

    @Column(name = EMAIL, nullable = false)
    private String email;

    @Column(name = DATE_BIRTHDAY, nullable = false)
    private LocalDate dateBirthday;

    @Column(name = DATE_CREATED, nullable = false, updatable = false)
    private LocalDate dateCreated;

    @Column(name = SALT, nullable = false, updatable = false)
    private byte[] salt;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
}
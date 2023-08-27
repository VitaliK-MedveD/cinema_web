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

@Entity
@Table(name = "PERSON")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = ID)
    private Long id;

    @Enumerated(STRING)
    @Column(name = ROLE)
    private Role role;

    @Column(unique = true, name = LOGIN, nullable = false)
    private String login;

    @Column(name = PASSWORD)
    private byte[] password;

    @Column(name = FIRST_NAME, nullable = false)
    private String firstName;

    @Column(name = EMAIL, nullable = false)
    private String email;

    @Column(name = DATE_BIRTHDAY, nullable = false)
    private LocalDate dateBirthday;

    @Column(name = DATE_CREATED)
    private LocalDate dateCreated;

    @Column(name = SALT)
    private byte[] salt;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
}
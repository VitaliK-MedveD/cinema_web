package by.it.medved.entities;

import by.it.medved.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.it.medved.util.Columns.*;
import static javax.persistence.EnumType.*;

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

    @Column(unique = true, name = LOGIN)
    private String login;

    @Column(name = PASSWORD)
    private byte[] password;

    @Column(name = FIRST_NAME)
    private String firstName;

    @Column(name = EMAIL)
    private String email;

    @Column(name = DATE_BIRTHDAY)
    private LocalDate dateBirthday;

    @Column(name = DATE_CREATED)
    private LocalDate dateCreated;

    @Column(name = SALT)
    private byte[] salt;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

    public void addTicket(Ticket ticket){
        if (this.tickets == null){
            this.tickets = new ArrayList<>();
        }
        this.tickets.add(ticket);
        ticket.setUser(this);
    }

    public void removeTicket(Ticket ticket){
        this.tickets.removeIf(removeTicket -> removeTicket.getId().equals(ticket.getId()));
        ticket.setUser(null);
    }

    public void returnTickets (){
        if (!tickets.isEmpty()){
            tickets.stream()
                    .forEach(ticket -> ticket.setUser(null));
        }
    }
}
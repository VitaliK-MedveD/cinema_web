package by.it.medved.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static by.it.medved.util.Columns.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MOVIE")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @Column(name = MOVIE_TITLE)
    private String movieTitle;

    @Column(name = SHOW_DATE_TIME)
    private LocalDateTime showDateTime;

    @Column(name = PRICE)
    private BigDecimal price;

    @Column(name = AGE_LIMIT)
    private int ageLimit;

    @Column(name = COUNT_FREE_TICKETS)
    private int countFreeTickets;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    public int getCount() {
        return (int) tickets.stream()
                .filter(ticket -> ticket.getUser() == null)
                .count();
    }

    public void updateTickets () {
        for (Ticket ticket : tickets) {
            ticket.setShowDateTime(showDateTime);
            ticket.setPrice(price);
        }
    }
}
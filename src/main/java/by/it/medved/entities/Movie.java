package by.it.medved.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static by.it.medved.util.Columns.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private Integer ageLimit;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;
}
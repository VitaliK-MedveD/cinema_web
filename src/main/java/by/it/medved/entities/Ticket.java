package by.it.medved.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static by.it.medved.util.Columns.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TICKET")
public class Ticket{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = MOVIE_ID)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = USER_ID)
    private User user;

    @Column(name = MOVIE_TITLE)
    private String movieTitle;

    @Column(name = SHOW_DATE_TIME)
    private LocalDateTime showDateTime;

    @Column(name = NUMBER_PLACE)
    private int numberOfPlace;

    @Column(name = PRICE)
    private BigDecimal price;

}
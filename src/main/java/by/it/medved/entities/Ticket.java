package by.it.medved.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    private Long id;
    private Long movieId;
    private Long userId;
    private String movieTitle;
    private LocalDate showDate;
    private LocalTime showTime;
    private int numberOfPlace;
    private int price;
}

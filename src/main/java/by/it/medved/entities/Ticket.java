package by.it.medved.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
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

package by.it.medved.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private Long id;
    private String movieTitle;
    private LocalDate showDate;
    private LocalTime showTime;
    private int price;
    private int ageLimit;
    private int freeTickets;
    private List<Ticket> tickets;

    public int getCountFreeTickets() {
        long count = tickets.stream()
                .filter(ticket -> ticket.getUserId() == null)
                .count();
        return (int) count;
    }
}

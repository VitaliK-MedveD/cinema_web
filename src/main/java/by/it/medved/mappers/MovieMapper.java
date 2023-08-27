package by.it.medved.mappers;

import by.it.medved.dto.MovieRequest;
import by.it.medved.dto.MovieResponse;
import by.it.medved.dto.UserResponse;
import by.it.medved.entities.Movie;
import by.it.medved.entities.User;
import by.it.medved.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MovieMapper {

    private final TicketService ticketService;

    public MovieResponse buildMovieResponse(Movie movie) {
        int freeTicketsCount = ticketService.getCountFreeTickets(movie.getId());
        return MovieResponse.builder()
                .id(movie.getId())
                .movieTitle(movie.getMovieTitle())
                .showDateTime(movie.getShowDateTime())
                .price(movie.getPrice())
                .ageLimit(movie.getAgeLimit())
                .countFreeTickets(freeTicketsCount)
                .build();
    }

    public Movie buildMovie(MovieRequest movieRequest) {
        return Movie.builder()
                .movieTitle(movieRequest.getMovieTitle())
                .showDateTime(LocalDateTime.parse(movieRequest.getShowDateTime()))
                .price(BigDecimal.valueOf(Double.parseDouble(movieRequest.getPrice())))
                .ageLimit(Integer.parseInt(movieRequest.getAgeLimit()))
                .build();
    }
}
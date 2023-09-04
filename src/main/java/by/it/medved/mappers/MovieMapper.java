package by.it.medved.mappers;

import by.it.medved.dto.request.MovieRequest;
import by.it.medved.dto.response.MovieResponse;
import by.it.medved.entities.Movie;
import by.it.medved.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieMapper {

    private final TicketService ticketService;

    public MovieResponse mapToMovieResponse(Movie movie) {
        Integer freeTicketsCount = ticketService.getFreeTicketsCount(movie.getId());
        return MovieResponse.builder()
                .id(movie.getId())
                .movieTitle(movie.getMovieTitle())
                .showDateTime(movie.getShowDateTime())
                .price(movie.getPrice())
                .ageLimit(movie.getAgeLimit())
                .freeTicketsCount(freeTicketsCount)
                .build();
    }

    public Movie mapToMovie(MovieRequest movieRequest) {
        return Movie.builder()
                .movieTitle(movieRequest.getMovieTitle())
                .showDateTime(movieRequest.getShowDateTime())
                .price(movieRequest.getPrice())
                .ageLimit(movieRequest.getAgeLimit())
                .build();
    }
}
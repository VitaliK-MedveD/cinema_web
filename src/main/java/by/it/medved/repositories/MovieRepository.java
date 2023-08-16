package by.it.medved.repositories;

import by.it.medved.entities.Movie;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface MovieRepository {

    Movie addMovie(Movie movie);

    Movie getMovieById(Long id);

    List<Movie> getMovies();

    Movie updateMovie(Long id, LocalDateTime showDateTime, BigDecimal price);

    void deleteMovieById(Long id);
}

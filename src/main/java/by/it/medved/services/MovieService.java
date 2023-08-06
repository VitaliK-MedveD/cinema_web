package by.it.medved.services;

import by.it.medved.entities.Movie;

import java.util.List;

public interface MovieService {

    Movie addMovie(Movie movie);

    Movie getMovieById(Long id);

    List<Movie> getAllMovies();

    Movie updateMovie(Long id, String showDateTime, String price);

    Movie deleteMovie(Long id);
}

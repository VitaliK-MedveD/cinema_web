package by.it.medved.services;

import by.it.medved.entities.Movie;

import java.util.List;

public interface MovieService {

    Movie addMovie(Movie movie);

    Movie getMovieById(Long id);

    Movie getMovieByTitle(String title);

    List<Movie> getAllMovies();

    Movie updateMovie(Movie movie);

    Movie deleteMovie(Movie movie);
}

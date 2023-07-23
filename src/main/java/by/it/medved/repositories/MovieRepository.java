package by.it.medved.repositories;

import by.it.medved.entities.Movie;

import java.util.List;

public interface MovieRepository {

    Movie addMovie(Movie movie);

    Movie getMovieById(Long id);

    Movie getMovieByTitle(String title);

    List<Movie> getAllMovies();

    Movie updateMovie(Movie movie);

    Movie deleteMovieById(Long id);
}

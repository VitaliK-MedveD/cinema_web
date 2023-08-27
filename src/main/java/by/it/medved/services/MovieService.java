package by.it.medved.services;

import by.it.medved.dto.MovieRequest;
import by.it.medved.dto.MovieResponse;
import by.it.medved.entities.Movie;

import java.util.List;

public interface MovieService {

    MovieResponse addMovie(MovieRequest movieRequest);

    MovieResponse getMovieById(Long id);

    List<MovieResponse> getMovies();

    MovieResponse updateMovie(Long id, String showDateTime, String price);

    void deleteMovie(Long id);
}
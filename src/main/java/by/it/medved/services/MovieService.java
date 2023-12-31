package by.it.medved.services;

import by.it.medved.dto.request.MovieRequest;
import by.it.medved.dto.request.UpdateMovieRequest;
import by.it.medved.dto.response.MovieResponse;

import java.util.List;

public interface MovieService {

    MovieResponse saveMovie(MovieRequest movieRequest);

    MovieResponse getMovieById(Long id);

    List<MovieResponse> getMovies();

    MovieResponse updateMovie(Long id, UpdateMovieRequest updateMovieRequest);

    void deleteMovie(Long id);
}
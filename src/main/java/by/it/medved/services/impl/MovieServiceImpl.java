package by.it.medved.services.impl;

import by.it.medved.dto.request.MovieRequest;
import by.it.medved.dto.response.MovieResponse;
import by.it.medved.entities.Movie;
import by.it.medved.mappers.MovieMapper;
import by.it.medved.repositories.MovieRepository;
import by.it.medved.services.MovieService;
import by.it.medved.services.TicketService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.it.medved.util.Message.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final TicketService ticketService;
    private final MovieMapper movieMapper;

    @Override
    public MovieResponse saveMovie(MovieRequest movieRequest) {
        Movie movie = movieMapper.mapToMovie(movieRequest);
        movie.setTickets(ticketService.addTenTickets(movie));
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.mapToMovieResponse(savedMovie);
    }

    @Override
    public MovieResponse getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(movieMapper::mapToMovieResponse)
                .orElseThrow(() -> new EntityNotFoundException(format(MOVIE_BY_ID_NOT_EXIST, id)));
    }

    @Override
    public List<MovieResponse> getMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::mapToMovieResponse)
                .toList();
    }

    @Override
    public MovieResponse updateMovie(Long id, MovieRequest movieRequest) {
        Movie updateMovie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format(MOVIE_BY_ID_NOT_EXIST, id)));
        updateMovie.setMovieTitle(movieRequest.getMovieTitle());
        updateMovie.setShowDateTime(movieRequest.getShowDateTime());
        updateMovie.setPrice(movieRequest.getPrice());
        updateMovie.setAgeLimit(movieRequest.getAgeLimit());
        Movie savedMovie = movieRepository.save(updateMovie);
        return movieMapper.mapToMovieResponse(savedMovie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
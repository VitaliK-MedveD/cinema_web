package by.it.medved.services.impl;

import by.it.medved.dto.request.MovieRequest;
import by.it.medved.dto.request.UpdateMovieRequest;
import by.it.medved.dto.response.MovieResponse;
import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.mappers.MovieMapper;
import by.it.medved.repositories.MovieRepository;
import by.it.medved.services.MovieService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static by.it.medved.util.Message.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    @Transactional
    public MovieResponse saveMovie(MovieRequest movieRequest) {
        Movie movie = movieMapper.mapToMovie(movieRequest);
        movie.setTickets(getTenTickets(movie));
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.mapToMovieResponse(savedMovie);
    }

    @Override
    @Transactional(readOnly = true)
    public MovieResponse getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(movieMapper::mapToMovieResponse)
                .orElseThrow(() -> new EntityNotFoundException(format(MOVIE_BY_ID_NOT_EXIST, id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieResponse> getMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::mapToMovieResponse)
                .toList();
    }

    @Override
    @Transactional
    public MovieResponse updateMovie(Long id, UpdateMovieRequest updateMovieRequest) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format(MOVIE_BY_ID_NOT_EXIST, id)));
        Movie updateMovie = updateFields(movie, updateMovieRequest);
        Movie savedMovie = movieRepository.save(updateMovie);
        return movieMapper.mapToMovieResponse(savedMovie);
    }

    @Override
    @Transactional
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    private List<Ticket> getTenTickets(Movie movie) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Ticket ticket = Ticket.builder()
                    .movie(movie)
                    .placeNumber(i + 1)
                    .build();
            tickets.add(ticket);
        }
        return tickets;
    }

    private Movie updateFields(Movie movie, UpdateMovieRequest updateMovieRequest) {
        if(updateMovieRequest != null) {
            if(updateMovieRequest.getMovieTitle() != null) {
                movie.setMovieTitle(updateMovieRequest.getMovieTitle());
            }
            if(updateMovieRequest.getShowDateTime() != null) {
                movie.setShowDateTime(updateMovieRequest.getShowDateTime());
            }
            if(updateMovieRequest.getPrice() != null) {
                movie.setPrice(updateMovieRequest.getPrice());
            }
            if(updateMovieRequest.getAgeLimit() != null) {
                movie.setAgeLimit(updateMovieRequest.getAgeLimit());
            }
        }
        return movie;
    }
}
package by.it.medved.services.impl;

import by.it.medved.dto.MovieRequest;
import by.it.medved.dto.MovieResponse;
import by.it.medved.entities.Movie;
import by.it.medved.mappers.MovieMapper;
import by.it.medved.repositories.MovieRepository;
import by.it.medved.services.MovieService;
import by.it.medved.services.TicketService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final TicketService ticketService;
    private final MovieMapper movieMapper;

    @Override
    public MovieResponse addMovie(MovieRequest movieRequest) {
        Movie movie = movieMapper.buildMovie(movieRequest);
        movie.setTickets(ticketService.addTenTickets(movie));
        movie.setCountFreeTickets(ticketService.getCountFreeTickets(movie.getId()));
        return movieMapper.buildMovieResponse(movieRepository.save(movie));
    }

    @Override
    public MovieResponse getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(movieMapper::buildMovieResponse)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id '" + id + "' does not exist"));
    }

    @Override
    public List<MovieResponse> getMovies() {
        return movieRepository.findAll().stream()
                .map(movieMapper::buildMovieResponse)
                .toList();
    }

    @Override
    public MovieResponse updateMovie(Long id, String showDateTime, String price) {
        LocalDateTime dateTime = LocalDateTime.parse(showDateTime);
        BigDecimal bigDecimalPrice = BigDecimal.valueOf(Double.parseDouble(price));
        Movie updateMovie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id '" + id + "' does not exist"));
        updateMovie.setShowDateTime(dateTime);
        updateMovie.setPrice(bigDecimalPrice);
        ticketService.updateMovieTickets(id, dateTime, bigDecimalPrice);
        return movieMapper.buildMovieResponse(movieRepository.save(updateMovie));
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
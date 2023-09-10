package by.it.medved.services.impl;

import by.it.medved.dto.request.MovieRequest;
import by.it.medved.dto.request.UpdateMovieRequest;
import by.it.medved.dto.response.MovieResponse;
import by.it.medved.entities.Movie;
import by.it.medved.mappers.MovieMapper;
import by.it.medved.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("MovieService tests")
@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {

    private static final Random RANDOM = new Random();

    @Autowired
    private MovieMapper movieMapper;
    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieServiceImpl movieService;
    private static MovieRequest movieRequest;
    private static UpdateMovieRequest updateMovieRequest;

    @BeforeAll
    static void beforeAll() {
        movieRequest = MovieRequest.builder()
                .movieTitle("Test movie")
                .showDateTime(LocalDateTime.of(2023, 11, 17, 20, 00))
                .price(BigDecimal.valueOf(10))
                .ageLimit(12)
                .build();
        updateMovieRequest = UpdateMovieRequest.builder()
                .movieTitle("Test movie2")
                .showDateTime(LocalDateTime.of(2023, 12, 15, 18, 00))
                .price(BigDecimal.valueOf(8))
                .ageLimit(16)
                .build();
    }

    @BeforeEach
    void init() {
        movieService = new MovieServiceImpl(movieRepository, movieMapper);
    }

    @Test
    @DisplayName("Get movie test by id")
    void getMovieByIdTest() {
        Movie movie = getMovie();
        when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));
        MovieResponse response = movieService.getMovieById(movie.getId());
        assertNotNull(response);
        assertEquals(movie.getId(), response.getId());
        verify(movieRepository).findById(movie.getId());
    }

    @Test
    @DisplayName("Get movies test")
    void getMoviesTest() {
        List<Movie> movies = getMovies();
        when(movieRepository.findAll()).thenReturn(movies);
        List<MovieResponse> responses = movieService.getMovies();
        assertNotNull(responses);
        assertEquals(3, responses.size());
        verify(movieRepository).findAll();
    }

    @Test
    @DisplayName("Movie update test")
    void updateMovieTest() {
        Movie movie = getMovie();
        when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));
        when(movieRepository.save(movie)).thenReturn(movie);
        MovieResponse response = movieService.updateMovie(movie.getId(), updateMovieRequest);
        assertNotNull(response);
        assertEquals(updateMovieRequest.getMovieTitle(), response.getMovieTitle());
        assertEquals(updateMovieRequest.getShowDateTime(), response.getShowDateTime());
    }

    private Movie getMovie() {
        Movie movie = movieMapper.mapToMovie(movieRequest);
        movie.setId(RANDOM.nextLong());
        return movie;
    }

    private List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            movies.add(getMovie());
        }
        return movies;
    }
}
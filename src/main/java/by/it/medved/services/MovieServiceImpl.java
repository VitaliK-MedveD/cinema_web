package by.it.medved.services;

import by.it.medved.entities.Movie;
import by.it.medved.repositories.MovieRepository;
import by.it.medved.repositories.MovieRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static by.it.medved.services.TicketServiceImpl.getTicketService;

public class MovieServiceImpl implements MovieService {

    private final TicketService ticketService = getTicketService();
    private final MovieRepository movieRepository = new MovieRepositoryImpl();

    private static volatile MovieService movieService;

    public static MovieService getMovieService() {
        if (movieService == null) {
            synchronized (MovieService.class) {
                if (movieService == null) {
                    movieService = new MovieServiceImpl();
                }
            }
        }
        return movieService;
    }

    @Override
    public Movie addMovie(Movie movie) {
        movie.setTickets(ticketService.addTenTickets(movie));
        movie.setCountFreeTickets(movie.getCount());
        return movieRepository.addMovie(movie);
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.getMovieById(id);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    @Override
    public Movie updateMovie(Long id, String showDateTime, String price) {
        LocalDateTime DateTime = LocalDateTime.parse(showDateTime);
        BigDecimal bigDecimalPrice = BigDecimal.valueOf(Double.parseDouble(price));
        return movieRepository.updateMovie(id, DateTime, bigDecimalPrice);
    }

    @Override
    public Movie deleteMovie(Long id) {
        return movieRepository.deleteMovieById(id);
    }

    private MovieServiceImpl() {
    }
}
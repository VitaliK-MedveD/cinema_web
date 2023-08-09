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
        Movie movie = movieRepository.getMovieById(id);
        movie.setCountFreeTickets(ticketService.getCountFreeTickets(movie.getId()));
        return movie;
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = movieRepository.getMovies();
        for (Movie movie : movies) {
            movie.setCountFreeTickets(ticketService.getCountFreeTickets(movie.getId()));
        }
        return movies;
    }

    @Override
    public Movie updateMovie(Long id, String showDateTime, String price) {
        LocalDateTime dateTime = LocalDateTime.parse(showDateTime);
        BigDecimal bigDecimalPrice = BigDecimal.valueOf(Double.parseDouble(price));
        ticketService.updateMovieTickets(id, dateTime, bigDecimalPrice);
        return movieRepository.updateMovie(id, dateTime, bigDecimalPrice);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteMovieById(id);
    }

    private MovieServiceImpl() {
    }
}
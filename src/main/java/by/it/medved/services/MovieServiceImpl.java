package by.it.medved.services;

import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.repositories.MovieRepository;
import by.it.medved.repositories.MovieRepositoryImpl;
import by.it.medved.util.DataBase;

import java.util.List;

import static by.it.medved.services.TicketServiceImpl.getTicketService;

public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository = new MovieRepositoryImpl();
    private final TicketService ticketService = getTicketService();
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
        Movie thisMovie = movieRepository.addMovie(movie);
        ticketService.addTenTickets(thisMovie);
        thisMovie.setTickets(ticketService.getAllTickets(thisMovie.getId(), DataBase.MOVIE_ID));
        thisMovie.setFreeTickets(thisMovie.getCountFreeTickets());
        return thisMovie;
    }

    @Override
    public Movie getMovieById(Long id) {
        Movie movie = movieRepository.getMovieById(id);
        movie.setTickets(ticketService.getAllTickets(movie.getId(), DataBase.MOVIE_ID));
        movie.setFreeTickets(movie.getCountFreeTickets());
        return movie;
    }

    @Override
    public Movie getMovieByTitle(String title) {
        Movie movie = movieRepository.getMovieByTitle(title);
        movie.setTickets(ticketService.getAllTickets(movie.getId(), DataBase.MOVIE_ID));
        movie.setFreeTickets(movie.getCountFreeTickets());
        return movie;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.getAllMovies();
        for (Movie movie : movies) {
            movie.setTickets(ticketService.getAllTickets(movie.getId(), DataBase.MOVIE_ID));
            movie.setFreeTickets(movie.getCountFreeTickets());
        }
        return movies;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        List<Ticket> tickets = movie.getTickets();
        for (Ticket ticket : tickets) {
            ticket.setShowDate(movie.getShowDate());
            ticket.setShowTime(movie.getShowTime());
            ticket.setPrice(movie.getPrice());
            ticketService.updateTicket(ticket);
        }
        return movieRepository.updateMovie(movie);
    }

    @Override
    public Movie deleteMovie(Movie movie) {
        return movieRepository.deleteMovieById(movie.getId());
    }

    private MovieServiceImpl() {
    }
}
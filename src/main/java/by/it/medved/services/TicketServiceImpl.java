package by.it.medved.services;

import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;
import by.it.medved.repositories.TicketRepository;
import by.it.medved.repositories.TicketRepositoryImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import static by.it.medved.util.Message.*;

public class TicketServiceImpl implements TicketService {

    Ticket freeTicket;
    private String errorMessage;
    private final TicketRepository ticketRepository = new TicketRepositoryImpl();
    private static volatile TicketService ticketService;

    public static TicketService getTicketService() {
        if (ticketService == null) {
            synchronized (UserService.class) {
                if (ticketService == null) {
                    ticketService = new TicketServiceImpl();
                }
            }
        }
        return ticketService;
    }

    @Override
    public boolean addTenTickets(Movie movie) {
        for (int i = 0; i < 10; i++) {
            Ticket ticket = Ticket.builder()
                    .movieId(movie.getId())
                    .movieTitle(movie.getMovieTitle())
                    .showDate(movie.getShowDate())
                    .showTime(movie.getShowTime())
                    .numberOfPlace(i + 1)
                    .price(movie.getPrice())
                    .build();
            ticketRepository.createTicket(ticket);
        }
        return true;
    }

    @Override
    public boolean buyTicket(User user, Movie movie) {
        if (checkDateTime(movie.getShowDate(), movie.getShowTime(), 0L) &&
                checkAge(movie.getAgeLimit(), user.getDateBirthday()) && checkFreeTickets(movie)) {
            return ticketRepository.buyOrReturnTicket(freeTicket.getId(), user.getId());
        } else return false;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public List<Ticket> getAllTickets(Long id, String columnName) {
        return ticketRepository.getAllTickets(id, columnName);
    }

    @Override
    public boolean updateTicket(Ticket ticket) {
        return ticketRepository.updateTicket(ticket);
    }

    @Override
    public boolean deleteTicket(Ticket ticket) {
        return ticketRepository.deleteTicketById(ticket.getId());
    }

    private boolean checkAge(int ageLimit, LocalDate birthDate) {
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age >= ageLimit) {
            return true;
        } else {
            errorMessage = INVALID_AGE.replace("%d", String.valueOf(ageLimit));
            return false;
        }
    }

    private boolean checkDateTime(LocalDate date, LocalTime time, long behindMinutes) {
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        if (dateTime.minusMinutes(behindMinutes).isAfter(LocalDateTime.now())) {
            return true;
        } else {
            errorMessage = INVALID_DATE;
            return false;
        }
    }

    private boolean checkFreeTickets(Movie movie) {
        Optional<Ticket> ticket = movie.getTickets().stream()
                .filter(t -> t.getUserId() == 0)
                .findFirst();
        if (ticket.isPresent()) {
            freeTicket = ticket.get();
            return true;
        } else {
            errorMessage = INVALID_PLACES;
            return false;
        }
    }

    private TicketServiceImpl() {
    }
}
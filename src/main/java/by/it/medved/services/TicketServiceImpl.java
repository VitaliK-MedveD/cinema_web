package by.it.medved.services;

import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;
import by.it.medved.repositories.TicketRepository;
import by.it.medved.repositories.TicketRepositoryImpl;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.it.medved.util.Message.*;

public class TicketServiceImpl implements TicketService {

    private Ticket freeTicket;
    private String errorMessage;
    private final TicketRepository ticketRepository = new TicketRepositoryImpl();
    private static TicketService ticketService;

    public static TicketService getTicketService() {
        if (ticketService == null) {
            ticketService = new TicketServiceImpl();
        }
        return ticketService;
    }

    @Override
    public List<Ticket> addTenTickets(Movie movie) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Ticket ticket = Ticket.builder()
                    .movie(movie)
                    .movieTitle(movie.getMovieTitle())
                    .showDateTime(movie.getShowDateTime())
                    .numberOfPlace(i + 1)
                    .price(movie.getPrice())
                    .build();
            tickets.add(ticket);
        }
        return tickets;
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.getTicketById(id);
    }

    @Override
    public List<Ticket> getUserTickets(Long userId) {
        return ticketRepository.getUserTickets(userId);
    }

    @Override
    public List<Ticket> getMovieTickets(Long movieId) {
        List<Ticket> tickets = ticketRepository.getMovieTickets(movieId);
        Comparator<Ticket> comparator = Comparator.comparing(Ticket::getNumberOfPlace);
        tickets.sort(comparator);
        return tickets;
    }

    @Override
    public void updateMovieTickets(Long movieId, LocalDateTime showDateTime, BigDecimal price) {
        List<Ticket> tickets = getMovieTickets(movieId);
        for (Ticket ticket : tickets) {
            ticketRepository.updateMovieTicket(ticket.getId(), showDateTime, price);
        }
    }

    @Override
    public int getCountFreeTickets(Long movieId) {
        return (int) getMovieTickets(movieId).stream()
                .filter(ticket -> ticket.getUser() == null)
                .count();
    }

    @Override
    public boolean buyTicket(User user, Movie movie) {
        if (checkDateTime(movie.getShowDateTime()) &&
                checkAge(movie.getAgeLimit(), user.getDateBirthday()) && checkFreeTickets(movie)) {
            return ticketRepository.buyTicket(freeTicket.getId(), user);
        } else return false;
    }

    @Override
    public boolean returnTicket(Ticket ticket) {
        if (checkDateTime(ticket.getShowDateTime(), 10L)) {
            return ticketRepository.returnTicket(ticket.getId());
        } else return false;
    }

    @Override
    public void returnUserTickets(Long userId) {
        List<Ticket> tickets = getUserTickets(userId);
        returnTicketList(tickets);
    }

    @Override
    public void returnMovieTickets(Long movieId) {
        List<Ticket> tickets = getMovieTickets(movieId).stream()
                .filter(ticket -> ticket.getUser() != null)
                .collect(Collectors.toList());
        returnTicketList(tickets);
    }

    @Override
    public void deleteMovieTickets(Long movieId){
        getMovieTickets(movieId).stream()
                .forEach(ticket -> ticketRepository.deleteTicket(ticket.getId()));
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public Ticket getTicket() {
        return freeTicket;
    }

    private void returnTicketList(List<Ticket> tickets) {
        if (CollectionUtils.isNotEmpty(tickets)) {
            for (Ticket ticket : tickets) {
                ticketRepository.returnTicket(ticket.getId());
            }
        }
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

    private boolean checkDateTime(LocalDateTime showDateTime) {
        if (showDateTime.isAfter(LocalDateTime.now())) {
            return true;
        } else {
            errorMessage = INVALID_DATE_TO_BUY;
            return false;
        }
    }

    private boolean checkDateTime(LocalDateTime showDateTime, long behindMinutes) {
        if (showDateTime.minusMinutes(behindMinutes).isAfter(LocalDateTime.now())) {
            return true;
        } else {
            errorMessage = INVALID_DATE_TO_RETURN;
            return false;
        }
    }

    private boolean checkFreeTickets(Movie movie) {
        Optional<Ticket> ticket = getMovieTickets(movie.getId()).stream()
                .filter(t -> t.getUser() == null)
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
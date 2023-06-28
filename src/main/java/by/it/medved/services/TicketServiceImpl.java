package by.it.medved.services;

import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;
import by.it.medved.repositories.TicketRepository;
import by.it.medved.repositories.TicketRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class TicketServiceImpl implements TicketService{

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
    public boolean createTenTickets(Movie movie) {
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
        Optional<Ticket> freeTicket = movie.getTickets().stream()
                .filter(ticket -> ticket.getUserId() == 0)
                .findFirst();
        if (freeTicket.isPresent()) {
            Ticket ticket = freeTicket.get();
            return ticketRepository.buyOrReturnTicket(ticket.getId(), user.getId());
        } else return false;
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

    private TicketServiceImpl() {
    }
}
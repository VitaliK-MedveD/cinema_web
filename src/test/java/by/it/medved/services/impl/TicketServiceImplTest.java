package by.it.medved.services.impl;

import by.it.medved.dto.request.BuyTicketsRequest;
import by.it.medved.dto.response.TicketResponse;
import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;
import by.it.medved.mappers.TicketMapper;
import by.it.medved.repositories.TicketRepository;
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

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("TicketService tests")
@ExtendWith(MockitoExtension.class)
public class TicketServiceImplTest {

    private static final Random RANDOM = new Random();

    @Mock
    private TicketRepository ticketRepository;
    @Autowired
    private TicketMapper ticketMapper;
    @InjectMocks
    private TicketServiceImpl ticketService;
    private static BuyTicketsRequest buyTicketsRequest;

    @BeforeAll
    static void beforeAll() {

        buyTicketsRequest = BuyTicketsRequest.builder()
                .ticketsId(List.of(1L))
                .build();
    }

    @BeforeEach
    void init() {
        ticketService = new TicketServiceImpl(ticketRepository, ticketMapper);
    }

    @Test
    @DisplayName("Buy tickets test")
    void buyTicketsTest() {
        Ticket ticket = getTicket();
        User user = User.builder()
                .id(RANDOM.nextLong())
                .build();
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        List<TicketResponse> responses = ticketService.buyTickets(user, buyTicketsRequest);
        assertNotNull(responses);
        assertEquals(1, responses.size());
    }

    @Test
    @DisplayName("Get ticket test by id")
    void getTicketByIdTest() {
        Ticket ticket = getTicket();
        when(ticketRepository.findById(ticket.getId())).thenReturn(Optional.of(ticket));
        TicketResponse response = ticketService.getTicketById(ticket.getId());
        assertNotNull(response);
        assertNotNull(response.getMovieId());
        assertNotNull(response.getPlaceNumber());
        assertEquals(ticket.getId(), response.getId());
        verify(ticketRepository).findById(ticket.getId());
    }

    @Test
    @DisplayName("Get user tickets test")
    void getUserTicketsTest() {
        Ticket ticket = getTicket();
        Long userId = ticket.getUser().getId();
        when(ticketRepository.findTicketsByUserId(userId)).thenReturn(List.of(ticket));
        List<TicketResponse> responses = ticketService.getUserTickets(userId);
        assertNotNull(responses);
        assertEquals(1, responses.size());
        verify(ticketRepository).findTicketsByUserId(userId);
    }

    @Test
    @DisplayName("Get movie tickets test")
    void getMovieTicketsTest() {
        Ticket ticket = getTicket();
        Long movieId = ticket.getMovie().getId();
        when(ticketRepository.findTicketsByMovieId(movieId)).thenReturn(List.of(ticket));
        List<TicketResponse> responses = ticketService.getMovieTickets(movieId);
        assertNotNull(responses);
        assertEquals(1, responses.size());
        verify(ticketRepository).findTicketsByMovieId(movieId);
    }

    @Test
    @DisplayName("Free tickets count test")
    void getFreeTicketsCountTest() {
        Ticket ticket = getTicket();
        Long movieId = ticket.getMovie().getId();
        when(ticketRepository.findTicketsByMovieIdAndUserNull(movieId)).thenReturn(List.of(ticket));
        Integer count = ticketService.getFreeTicketsCount(movieId);
        assertNotNull(count);
        assertEquals(1, count);
    }


    private Ticket getTicket() {
        Movie movie = Movie.builder()
                .id(RANDOM.nextLong())
                .build();
        User user = User.builder()
                .id(RANDOM.nextLong())
                .build();
        return Ticket.builder()
                .id(RANDOM.nextLong())
                .movie(movie)
                .user(user)
                .placeNumber(1)
                .build();
    }
}
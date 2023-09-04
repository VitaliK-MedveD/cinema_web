package by.it.medved.repositories;

import by.it.medved.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findTicketsByUserId(Long userId);

    List<Ticket> findTicketsByMovieId(Long movieId);

    List<Ticket> findTicketsByMovieIdAndUserNull(Long movieId);
}
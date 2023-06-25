package by.it.medved.repositories;

import by.it.medved.entities.Ticket;

import java.util.List;

public interface TicketRepository {

    boolean createTicket(Ticket ticket);
    List<Ticket> getAllTickets(Long id, String columnName);
    boolean buyOrReturnTicket(Long ticketId, Long personId);
    boolean updateTicket(Ticket ticket);
    boolean deleteTicketById(Long id);
}

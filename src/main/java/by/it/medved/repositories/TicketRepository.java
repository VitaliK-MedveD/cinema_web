package by.it.medved.repositories;

import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;

public interface TicketRepository {

    Ticket createTicket(Ticket ticket);

    Ticket getTicketById(Long id);

    boolean buyTicket(Long ticketId, User user);

    boolean returnTicket(Long ticketId);
}

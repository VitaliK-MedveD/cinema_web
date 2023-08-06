package by.it.medved.repositories;

import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;

import javax.persistence.EntityManager;

import static by.it.medved.util.JpaUtil.getEntityManager;

public class TicketRepositoryImpl implements TicketRepository {

    @Override
    public Ticket createTicket(Ticket ticket) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(ticket);
        entityManager.getTransaction().commit();
        entityManager.close();
        return ticket;
    }

    @Override
    public Ticket getTicketById(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Ticket ticket = entityManager.find(Ticket.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return ticket;
    }

    @Override
    public boolean buyTicket(Long ticketId, User user) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Ticket ticket = entityManager.find(Ticket.class, ticketId);
        ticket.setUser(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean returnTicket(Long ticketId) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Ticket ticket = entityManager.find(Ticket.class, ticketId);
        ticket.setUser(null);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }
}
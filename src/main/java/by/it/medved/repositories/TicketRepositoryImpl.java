package by.it.medved.repositories;

import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static by.it.medved.util.FieldsEntities.*;
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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> ticketCriteriaQuery = criteriaBuilder.createQuery(Ticket.class);
        Root<Ticket> ticketRoot = ticketCriteriaQuery.from(Ticket.class);
        ticketCriteriaQuery.select(ticketRoot)
                .where(criteriaBuilder.equal(ticketRoot.get(ID), id));
        Ticket ticket = entityManager.createQuery(ticketCriteriaQuery).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return ticket;
    }

    @Override
    public List<Ticket> getUserTickets(Long userId) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> userTicketsCriteriaQuery = criteriaBuilder.createQuery(Ticket.class);
        Root<User> userRoot = userTicketsCriteriaQuery.from(User.class);
        userTicketsCriteriaQuery.select(userRoot.get(TICKETS))
                .where(criteriaBuilder.equal(userRoot.get(ID), userId));
        return entityManager.createQuery(userTicketsCriteriaQuery).getResultList();
    }

    @Override
    public List<Ticket> getMovieTickets(Long movieId) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> movieTicketsCriteriaQuery = criteriaBuilder.createQuery(Ticket.class);
        Root<Movie> movieRoot = movieTicketsCriteriaQuery.from(Movie.class);
        movieTicketsCriteriaQuery.select(movieRoot.get(TICKETS))
                .where(criteriaBuilder.equal(movieRoot.get(ID), movieId));
        return entityManager.createQuery(movieTicketsCriteriaQuery).getResultList();
    }

    @Override
    public void updateMovieTicket(Long ticketId, LocalDateTime showDateTime, BigDecimal price) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Ticket> ticketCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Ticket.class);
        Root<Ticket> ticketRoot = ticketCriteriaUpdate.from(Ticket.class);
        ticketCriteriaUpdate.set(SHOW_DATE_TIME, showDateTime);
        ticketCriteriaUpdate.set(PRICE, price);
        ticketCriteriaUpdate.where(criteriaBuilder.equal(ticketRoot.get(ID), ticketId));
        entityManager.createQuery(ticketCriteriaUpdate).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public int getCountFreeTickets (Long movieId){
        List<Ticket> tickets = getMovieTickets(movieId);
        return (int) tickets.stream()
                .filter(ticket -> ticket.getUser() == null)
                .count();
    }

    @Override
    public boolean buyTicket(Long ticketId, User user) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Ticket> ticketCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Ticket.class);
        Root<Ticket> ticketRoot = ticketCriteriaUpdate.from(Ticket.class);
        ticketCriteriaUpdate.set(USER, user)
                .where(criteriaBuilder.equal(ticketRoot.get(ID), ticketId));
        entityManager.createQuery(ticketCriteriaUpdate).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean returnTicket(Long ticketId) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Ticket> ticketCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Ticket.class);
        Root<Ticket> ticketRoot = ticketCriteriaUpdate.from(Ticket.class);
        ticketCriteriaUpdate.set(USER, null)
                .where(criteriaBuilder.equal(ticketRoot.get(ID), ticketId));
        entityManager.createQuery(ticketCriteriaUpdate).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }
}
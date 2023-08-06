package by.it.medved.repositories;

import by.it.medved.entities.Movie;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static by.it.medved.util.JpaUtil.getEntityManager;
import static by.it.medved.util.JpqlQuery.READ_ALL_MOVIES;

public class MovieRepositoryImpl implements MovieRepository {

    @Override
    public Movie addMovie(Movie movie) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();
        entityManager.close();
        return movie;
    }

    @Override
    public Movie getMovieById(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Movie movie = entityManager.find(Movie.class, id);
        movie.setCountFreeTickets(movie.getCount());
        entityManager.getTransaction().commit();
        entityManager.close();
        return movie;
    }

    @Override
    public List<Movie> getAllMovies() {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        List<Movie> movies = entityManager.createQuery(READ_ALL_MOVIES, Movie.class)
                .getResultList();
        for (Movie movie : movies) {
            movie.setCountFreeTickets(movie.getCount());
        }
        entityManager.close();
        return movies;
    }

    @Override
    public Movie updateMovie(Long id, LocalDateTime showDateTime, BigDecimal price) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Movie movie = entityManager.find(Movie.class, id);
        movie.setShowDateTime(showDateTime);
        movie.setPrice(price);
        movie.updateTickets();
        entityManager.getTransaction().commit();
        entityManager.close();
        return movie;
    }

    @Override
    public Movie deleteMovieById(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Movie movie = entityManager.find(Movie.class, id);
        entityManager.remove(movie);
        entityManager.getTransaction().commit();
        entityManager.close();
        return movie;
    }
}
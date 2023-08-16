package by.it.medved.repositories;

import by.it.medved.entities.Movie;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static by.it.medved.util.FieldsEntities.*;
import static by.it.medved.util.JpaUtil.getEntityManager;

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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> movieCriteriaQuery = criteriaBuilder.createQuery(Movie.class);
        Root<Movie> movieRoot = movieCriteriaQuery.from(Movie.class);
        movieCriteriaQuery
                .select(movieRoot)
                .where(criteriaBuilder.equal(movieRoot.get(ID), id));
        Movie movie = entityManager.createQuery(movieCriteriaQuery).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return movie;
    }

    @Override
    public List<Movie> getMovies() {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> movieCriteriaQuery = criteriaBuilder.createQuery(Movie.class);
        Root<Movie> movieRoot = movieCriteriaQuery.from(Movie.class);
        movieCriteriaQuery.select(movieRoot);
        List<Movie> movies = entityManager.createQuery(movieCriteriaQuery).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return movies;
    }

    @Override
    public Movie updateMovie(Long id, LocalDateTime showDateTime, BigDecimal price) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Movie> movieCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Movie.class);
        Root<Movie> movieRoot = movieCriteriaUpdate.from(Movie.class);
        movieCriteriaUpdate
                .set(SHOW_DATE_TIME, showDateTime)
                .set(PRICE, price)
                .where(criteriaBuilder.equal(movieRoot.get(ID), id));
        entityManager.createQuery(movieCriteriaUpdate).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return getMovieById(id);
    }

    @Override
    public void deleteMovieById(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Movie> movieCriteriaDelete = criteriaBuilder.createCriteriaDelete(Movie.class);
        Root<Movie> movieRoot = movieCriteriaDelete.from(Movie.class);
        movieCriteriaDelete.where(criteriaBuilder.equal(movieRoot.get(ID), id));
        entityManager.createQuery(movieCriteriaDelete).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
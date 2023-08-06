package by.it.medved.util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JpaUtil {

    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence
                    .createEntityManagerFactory("CinemaWebPersistence");
            return entityManagerFactory.createEntityManager();
    }

    private JpaUtil() {
    }
}
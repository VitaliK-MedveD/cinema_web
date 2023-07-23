package by.it.medved.util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory
                    ("CinemaWebPersistence");
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static void deleteEntityManager() {
        if (entityManager != null) {
            entityManager = null;
        }
    }

    private JpaUtil() {
    }
}
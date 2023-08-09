package by.it.medved.repositories;

import by.it.medved.entities.User;
import by.it.medved.enums.Role;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static by.it.medved.util.FieldsEntities.*;
import static by.it.medved.util.JpaUtil.getEntityManager;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User createUser(User user) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public User getUserById(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userRoot)
                .where(criteriaBuilder.equal(userRoot.get(ID), id));
        User user = entityManager.createQuery(userCriteriaQuery).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        List<User> users = getUsers();
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }

    @Override
    public List<User> getUsers() {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userRoot);
        List<User> users = entityManager.createQuery(userCriteriaQuery).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return users;
    }

    @Override
    public boolean updateRole(Long id, Role role) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<User> userCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> userRoot = userCriteriaUpdate.from(User.class);
        userCriteriaUpdate.set(ROLE, role)
                .where(criteriaBuilder.equal(userRoot.get(ID), id));
        entityManager.createQuery(userCriteriaUpdate).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public User updateUserFields(Long id, String firstName, String email, String dateBirthday) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<User> userCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> userRoot = userCriteriaUpdate.from(User.class);
        userCriteriaUpdate.set(FIRST_NAME, firstName);
        userCriteriaUpdate.set(EMAIL, email);
        userCriteriaUpdate.set(DATE_BIRTHDAY, LocalDate.parse(dateBirthday));
        userCriteriaUpdate.where(criteriaBuilder.equal(userRoot.get(ID), id));
        entityManager.createQuery(userCriteriaUpdate).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return getUserById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<User> userCriteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
        Root<User> userRoot = userCriteriaDelete.from(User.class);
        userCriteriaDelete.where(criteriaBuilder.equal(userRoot.get(ID), id));
        entityManager.createQuery(userCriteriaDelete).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
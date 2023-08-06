package by.it.medved.repositories;

import by.it.medved.entities.User;
import by.it.medved.enums.Role;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static by.it.medved.util.JpaUtil.getEntityManager;
import static by.it.medved.util.JpqlQuery.READ_ALL_USERS;

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
        User user = entityManager.find(User.class, id);
        Hibernate.initialize(user.getTickets());
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public Optional<User> getUserByLogin(String login){
        List<User> users = getAllUsers();
        Optional<User> optionalUser = users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
        return optionalUser;
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery(READ_ALL_USERS, User.class)
                .getResultList();
        entityManager.close();
        return users;
    }

    @Override
    public boolean updateRole(Long id, Role role) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        user.setRole(role);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public User updateUserFields(Long id, String firstName, String email, String dateBirthday) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        user.setFirstName(firstName);
        user.setEmail(email);
        user.setDateBirthday(LocalDate.parse(dateBirthday));
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public void deleteUserById(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        user.returnTickets();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
package by.it.medved.repositories;

import by.it.medved.entities.Role;
import by.it.medved.entities.User;
import by.it.medved.util.ConnectionManager;
import by.it.medved.util.DataBase;
import by.it.medved.util.JpaUtil;
import by.it.medved.util.SqlRequest;

import javax.persistence.EntityManager;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static by.it.medved.util.JpqlQuery.*;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User createUser(User user) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        JpaUtil.deleteEntityManager();
        return user;
    }

    @Override
    public User getUserById(Long id) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        JpaUtil.deleteEntityManager();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery(READ_ALL_USERS, User.class)
                .getResultList();
        entityManager.close();
        JpaUtil.deleteEntityManager();
        return users;
    }

    @Override
    public boolean updateRole(Long id, Role role) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        user.setRole(role);
        entityManager.getTransaction().commit();
        entityManager.close();
        JpaUtil.deleteEntityManager();
        return true;
    }

    @Override
    public User updateUserFields(Long id, String firstName, String email, String dateBirthday) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        user.setFirstName(firstName);
        user.setEmail(email);
        user.setDateBirthday(LocalDate.parse(dateBirthday));
        entityManager.getTransaction().commit();
        entityManager.close();
        JpaUtil.deleteEntityManager();
        return user;
    }

    @Override
    public boolean deleteUserById(Long id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(SqlRequest.DELETE_USER_BY_ID);
            statement.setLong(1, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = User.builder()
                .id(resultSet.getLong(DataBase.ID))
                .role(Role.valueOf(resultSet.getString(DataBase.ROLE)))
                .login(resultSet.getString(DataBase.LOGIN))
                .password(resultSet.getBytes(DataBase.PASSWORD))
                .firstName(resultSet.getString(DataBase.FIRST_NAME))
                .email(resultSet.getString(DataBase.EMAIL))
                .dateBirthday(resultSet.getDate(DataBase.DATE_BIRTHDAY).toLocalDate())
                .dateCreated(resultSet.getDate(DataBase.DATE_CREATED).toLocalDate())
                .salt(resultSet.getBytes(DataBase.SALT))
                .build();
        return user;
    }
}

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
import java.util.ArrayList;
import java.util.List;

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
    public User getUserByLogin(String login) {
        try (Connection connection = ConnectionManager.open()) {
            User user = new User();
            PreparedStatement statement = connection.prepareStatement(SqlRequest.GET_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(SqlRequest.GET_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = buildUser(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateRole(Long id, Role role) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(SqlRequest.UPDATE_ACCESS);
            statement.setString(1, role.name());
            statement.setLong(2, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
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

//        try (Connection connection = ConnectionManager.open()) {
//            PreparedStatement statement = connection.prepareStatement(SqlRequest.UPDATE_USER_FIELDS);
//            statement.setString(1, user.getFirstName());
//            statement.setString(2, user.getEmail());
//            statement.setDate(3, Date.valueOf(user.getDateBirthday()));
//            statement.setLong(4, user.getId());
//            statement.execute();
//            return true;
//        } catch (SQLException e) {
//            throw new RuntimeException();
//        }
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

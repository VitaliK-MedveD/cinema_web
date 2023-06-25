package by.it.medved.repositories;

import by.it.medved.entities.Access;
import by.it.medved.entities.User;
import by.it.medved.util.ConnectionManager;
import by.it.medved.util.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User createUser(User user) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO user (access, login, password, " +
                            "first_name, email, date_birthday, date_created, salt) VALUES (?,?,?,?,?,?,?,?)");
            statement.setString(1, user.getAccess().name());
            statement.setString(2, user.getLogin());
            statement.setBytes(3, user.getPassword());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getEmail());
            statement.setDate(6, Date.valueOf(user.getDateBirthday()));
            statement.setDate(7, Date.valueOf(user.getDateCreated()));
            statement.setBytes(8, user.getSalt());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return getUserByLogin(user.getLogin());
    }

    @Override
    public User getUserByLogin(String login) {
        try (Connection connection = ConnectionManager.open()) {
            User user = new User();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE login=?");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = buildUserFromDatabase(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(Long id) {
        try (Connection connection = ConnectionManager.open()) {
            User user = new User();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE id=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = buildUserFromDatabase(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = buildUserFromDatabase(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateAccess(Long id, Access access) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE user SET access =? " +
                    "WHERE id =?");
            statement.setString(1, access.name());
            statement.setLong(2, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean updateUserFields(User user) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE user SET first_name =?, email =?," +
                            "date_birthday =? WHERE id =?");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getEmail());
            statement.setDate(3, Date.valueOf(user.getDateBirthday()));
            statement.setLong(4, user.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id=?");
            statement.setLong(1, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    private User buildUserFromDatabase(ResultSet resultSet) throws SQLException {
        User user = User.builder()
                .id(resultSet.getLong(DataBase.ID))
                .access(Access.valueOf(resultSet.getString(DataBase.ACCESS)))
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

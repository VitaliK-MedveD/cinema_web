package by.it.medved.repositories;

import by.it.medved.entities.Access;
import by.it.medved.entities.User;

import java.util.List;

public interface UserRepository {

    User createUser (User user);
    User getUserByLogin(String login);
    User getById(Long id);
    List<User> getAllUsers();
    boolean updateAccess(Long id, Access access);
    boolean updateUserFields(User user);
    boolean deleteUserById(Long id);
}

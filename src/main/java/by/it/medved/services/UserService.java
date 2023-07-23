package by.it.medved.services;

import by.it.medved.entities.Role;
import by.it.medved.entities.User;
import by.it.medved.exception.UserWasNotCreatedException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    boolean updateRole(Long id, Role role);

    User updateUserFields(Long id, String firstName, String email, String dateBirthday);

    boolean deleteUserById(Long id);

    Optional<User> getUserByLogin(String login);

    boolean checkLine(String line, String regex);

    boolean checkDate(String line, String regex);

    boolean checkUniqueLogin(String login);
}

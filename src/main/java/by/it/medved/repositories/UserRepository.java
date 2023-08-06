package by.it.medved.repositories;

import by.it.medved.entities.User;
import by.it.medved.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User createUser(User user);

    User getUserById(Long id);

    Optional<User> getUserByLogin(String login);

    List<User> getAllUsers();

    boolean updateRole(Long id, Role role);

    User updateUserFields(Long id, String firstName, String email, String dateBirthday);

    void deleteUserById(Long id);
}

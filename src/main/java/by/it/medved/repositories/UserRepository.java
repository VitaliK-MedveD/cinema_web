package by.it.medved.repositories;

import by.it.medved.entities.Role;
import by.it.medved.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    boolean updateRole(Long id, Role role);

    User updateUserFields(Long id, String firstName, String email, String dateBirthday);

    boolean deleteUserById(Long id);
}

package by.it.medved.repositories;

import by.it.medved.entities.User;
import by.it.medved.enums.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User createUser(User user);

    User getUserById(Long id);

    List<User> getUsers();

    User changeUserPassword(Long userId, byte[] encryptedPassword);

    boolean updateRole(Long id, Role role);

    User updateUserFields(Long id, String firstName, String email, LocalDate dateBirthday);

    void deleteUserById(Long id);
}

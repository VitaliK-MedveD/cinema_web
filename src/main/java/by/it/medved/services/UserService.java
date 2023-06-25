package by.it.medved.services;

import by.it.medved.entities.Access;
import by.it.medved.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);
    User getById(Long id);
    List<User> getAllUsers();
    boolean updateAccess(Long id, Access access);
    User updateUserFields(Long id, String firstName, String email, String dateBirthday);
    boolean deleteById(Long id);
    Optional<User> getByLogin(String login);
    boolean checkLine(String line, String regex);
    boolean checkDate(String line, String regex);
    boolean checkUniqueLogin (String login);
}

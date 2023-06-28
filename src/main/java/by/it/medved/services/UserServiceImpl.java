package by.it.medved.services;

import by.it.medved.entities.Access;
import by.it.medved.entities.User;
import by.it.medved.repositories.UserRepository;
import by.it.medved.repositories.UserRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();
    private static volatile UserService userService;

    public static UserService getUserService() {
        if (userService == null) {
            synchronized (UserService.class) {
                if (userService == null) {
                    userService = new UserServiceImpl();
                }
            }
        }
        return userService;
    }

    @Override
    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public boolean updateAccess(Long id, Access access) {
        return userRepository.updateAccess(id, access);
    }

    @Override
    public User updateUserFields(Long id, String firstName, String email, String dateBirthday) {
        User user = userRepository.getUserById(id);
        user.setFirstName(firstName);
        user.setEmail(email);
        user.setDateBirthday(LocalDate.parse(dateBirthday));
        userRepository.updateUserFields(user);
        return user;
    }

    @Override
    public boolean deleteUserById(Long id) {
        return userRepository.deleteUserById(id);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        List<User> users = getAllUsers();
        Optional<User> optionalUser = users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
        return optionalUser;
    }

    @Override
    public boolean checkLine(String line, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }

    @Override
    public boolean checkDate(String line, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches() && LocalDate.parse(line).isBefore(LocalDate.now())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkUniqueLogin(String login) {
        List<User> users = getAllUsers();
        if (users.stream()
                .noneMatch(user -> user.getLogin().equalsIgnoreCase(login))) {
            return true;
        } else return false;
    }

    private UserServiceImpl() {
    }
}
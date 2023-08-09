package by.it.medved.services;

import by.it.medved.entities.User;
import by.it.medved.enums.Role;
import by.it.medved.repositories.UserRepository;
import by.it.medved.repositories.UserRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.it.medved.services.TicketServiceImpl.getTicketService;

public class UserServiceImpl implements UserService {

    private final TicketService ticketService = getTicketService();
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
    public Optional<User> getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getUsers();
    }

    @Override
    public boolean updateRole(Long id, Role role) {
        return userRepository.updateRole(id, role);
    }

    @Override
    public User updateUserFields(Long id, String firstName, String email, String dateBirthday) {
        return userRepository.updateUserFields(id, firstName, email, dateBirthday);
    }

    @Override
    public void deleteUserById(Long id) {
        ticketService.returnUserTickets(id);
        userRepository.deleteUserById(id);
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
        return (matcher.matches() && LocalDate.parse(line).isBefore(LocalDate.now()));
    }

    @Override
    public boolean checkUniqueLogin(String login) {
        List<User> users = getAllUsers();
        return (users.stream()
                .noneMatch(user -> user.getLogin().equalsIgnoreCase(login)));
    }

    private UserServiceImpl() {
    }
}
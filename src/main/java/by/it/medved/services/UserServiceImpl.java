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

import static by.it.medved.services.EncryptionServiceImpl.getEncryptionService;
import static by.it.medved.services.TicketServiceImpl.getTicketService;

public class UserServiceImpl implements UserService {

    private final EncryptionService encryptionService = getEncryptionService();
    private final TicketService ticketService = getTicketService();
    private final UserRepository userRepository = new UserRepositoryImpl();
    private static UserService userService;

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
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
        return getUsers().stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User changeUserPassword(String newPassword, Long userId){
        User user = getUserById(userId);
        byte[] encryptedPassword = encryptionService.getEncryptedPassword(newPassword, user.getSalt());
        return userRepository.changeUserPassword(userId, encryptedPassword);
    }

    @Override
    public boolean updateRole(Long id, Role role) {
        return userRepository.updateRole(id, role);
    }

    @Override
    public User updateUserFields(Long id, String firstName, String email, String dateBirthday) {
        LocalDate localDateBirthday = LocalDate.parse(dateBirthday);
        return userRepository.updateUserFields(id, firstName, email, localDateBirthday);
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
        List<User> users = getUsers();
        return (users.stream()
                .noneMatch(user -> user.getLogin().equalsIgnoreCase(login)));
    }

    private UserServiceImpl() {
    }
}
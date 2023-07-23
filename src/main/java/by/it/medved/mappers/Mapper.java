package by.it.medved.mappers;

import by.it.medved.entities.Movie;
import by.it.medved.entities.Role;
import by.it.medved.entities.User;
import by.it.medved.services.EncryptionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static by.it.medved.services.EncryptionServiceImpl.getEncryptionService;

public class Mapper {

    private final EncryptionService encryptionService = getEncryptionService();
    private static volatile Mapper mapper;

    public static Mapper getMapper() {
        if (mapper == null) {
            synchronized (Mapper.class) {
                if (mapper == null) {
                    mapper = new Mapper();
                }
            }
        }
        return mapper;
    }

    public User buildUser(String login, String password, String firstName, String email, String dateBirthday) {
        byte[] salt = encryptionService.generateSalt();
        byte[] encryptedPassword = encryptionService.getEncryptedPassword(password, salt);
        return User.builder()
                .role(Role.USER)
                .login(login)
                .password(encryptedPassword)
                .firstName(firstName)
                .email(email)
                .dateBirthday(LocalDate.parse(dateBirthday))
                .dateCreated(LocalDate.now())
                .salt(salt)
                .build();
    }

    public Movie buildMovie(String movieTitle, String showDate, String showTime, String price, String ageLimit) {
        Movie movie = Movie.builder()
                .movieTitle(movieTitle)
                .showDate(LocalDate.parse(showDate))
                .showTime(LocalTime.parse(showTime))
                .price(Integer.parseInt(price))
                .ageLimit(Integer.parseInt(ageLimit))
                .build();
        return movie;
    }

    private Mapper() {
    }
}
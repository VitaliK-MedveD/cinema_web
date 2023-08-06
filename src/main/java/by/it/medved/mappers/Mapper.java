package by.it.medved.mappers;

import by.it.medved.entities.Movie;
import by.it.medved.enums.Role;
import by.it.medved.entities.User;
import by.it.medved.services.EncryptionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

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
                .tickets(new ArrayList<>())
                .build();
    }

    public Movie buildMovie(String movieTitle, String showDate, String showTime, String price, String ageLimit) {
        return Movie.builder()
                .movieTitle(movieTitle)
                .showDateTime(LocalDateTime.of(LocalDate.parse(showDate), LocalTime.parse(showTime)))
                .price(BigDecimal.valueOf(Double.parseDouble(price)))
                .ageLimit(Integer.parseInt(ageLimit))
                .build();

    }

    private Mapper() {
    }
}
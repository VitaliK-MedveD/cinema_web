package by.it.medved.mappers;

import by.it.medved.entities.Access;
import by.it.medved.entities.Movie;
import by.it.medved.entities.Ticket;
import by.it.medved.entities.User;
import by.it.medved.services.EncryptionService;
import by.it.medved.services.EncryptionServiceImpl;
import by.it.medved.util.Regex;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
public class Mapper {

    private final EncryptionService encryptionService = new EncryptionServiceImpl();

    public User buildUser(String login, String password, String firstName, String email, String dateBirthday) {
        byte[] salt = encryptionService.generateSalt();
        byte[] encryptedPassword = encryptionService.getEncryptedPassword(password, salt);
        User user = User.builder()
                .access(Access.USER)
                .login(login)
                .password(encryptedPassword)
                .firstName(firstName)
                .email(email)
                .dateBirthday(LocalDate.parse(dateBirthday))
                .dateCreated(LocalDate.now())
                .salt(salt)
                .build();
        return user;
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
}

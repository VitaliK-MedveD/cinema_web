package by.it.medved.services;

import by.it.medved.dto.UserFieldsValidationDto;
import by.it.medved.util.Message;
import by.it.medved.util.Regex;

import static by.it.medved.services.UserServiceImpl.getUserService;

public class FiltrationServiceImpl implements FiltrationService{

    private final UserService userService = getUserService();
    private UserFieldsValidationDto userFieldsValidationDto;

    @Override
    public boolean checkUserFields(String login, String password, String firstName, String email, String dateBirthday) {
        userFieldsValidationDto = new UserFieldsValidationDto();
        if(checkUniqueLogin(login) & checkPassword(password) & checkFirstName(firstName) & checkEmail(email) &
                checkDateBirthday(dateBirthday)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUserFields(String firstName, String email, String dateBirthday) {
        userFieldsValidationDto = new UserFieldsValidationDto();
        if(checkFirstName(firstName) & checkEmail(email) &
                checkDateBirthday(dateBirthday)) {
            return true;
        }
        return false;
    }

    @Override
    public UserFieldsValidationDto getUserFieldsValidationDto() {
        return userFieldsValidationDto;
    }

    private boolean checkUniqueLogin (String login) {
        if (userService.checkUniqueLogin(login)) {
            userFieldsValidationDto.setLogin(login);
            return true;
        } else {
            userFieldsValidationDto.setNotValidLogin(login + Message.LOGIN_BUSY_MESSAGE);
            return false;
        }
    }

    private boolean checkPassword(String password) {
        if (userService.checkLine(password, Regex.PASSWORD)) {
            userFieldsValidationDto.setPassword(password);
            return true;
        } else {
            userFieldsValidationDto.setNotValidPassword(Message.REGEX_PASSWORD);
            return false;
        }
    }

    private boolean checkFirstName(String firstName) {
        if (userService.checkLine(firstName, Regex.FIRST_NAME)) {
            userFieldsValidationDto.setFirstName(firstName);
            return true;
        } else {
            userFieldsValidationDto.setNotValidFirstName(Message.REGEX_FIRST_NAME);
            return false;
        }
    }

    private boolean checkEmail(String email) {
        if (userService.checkLine(email, Regex.EMAIL)) {
            userFieldsValidationDto.setEmail(email);
            return true;
        } else {
            userFieldsValidationDto.setNotValidEmail(Message.REGEX_EMAIL);
            return false;
        }
    }

    private boolean checkDateBirthday(String dateBirthday) {
        if (userService.checkDate(dateBirthday, Regex.DATE)) {
            userFieldsValidationDto.setDateBirthday(dateBirthday);
            return true;
        } else {
            userFieldsValidationDto.setNotValidDateBirthday(Message.REGEX_DATE);
            return false;
        }
    }
}

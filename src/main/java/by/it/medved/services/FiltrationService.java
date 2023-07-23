package by.it.medved.services;

import by.it.medved.dto.UserFieldsValidationDto;

public interface FiltrationService {

    boolean checkUserFields(String login, String password, String firstName, String email, String dateBirthday);

    boolean checkUserFields(String firstName, String email, String dateBirthday);

    UserFieldsValidationDto getUserFieldsValidationDto();
}

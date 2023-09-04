package by.it.medved.mappers;

import by.it.medved.dto.request.UserRequest;
import by.it.medved.dto.response.UserResponse;
import by.it.medved.entities.User;
import by.it.medved.enums.Role;
import by.it.medved.services.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final EncryptionService encryptionService;

    public UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .role(user.getRole())
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .dateBirthday(user.getDateBirthday())
                .dateCreated(user.getDateCreated())
                .build();
    }

    public User mapToUser(UserRequest userRequest) {
        byte[] salt = encryptionService.generateSalt();
        byte[] encryptedPassword = encryptionService.getEncryptedPassword(userRequest.getPassword(), salt);
        return User.builder()
                .role(Role.USER)
                .login(userRequest.getLogin())
                .password(encryptedPassword)
                .firstName(userRequest.getFirstName())
                .email(userRequest.getEmail())
                .dateBirthday(userRequest.getDateBirthday())
                .dateCreated(LocalDate.now())
                .salt(salt)
                .build();
    }
}
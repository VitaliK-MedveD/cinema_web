package by.it.medved.services.impl;

import by.it.medved.dto.request.AuthenticationRequest;
import by.it.medved.dto.request.ChangePasswordRequest;
import by.it.medved.dto.request.UpdateUserRequest;
import by.it.medved.dto.request.UserRequest;
import by.it.medved.dto.response.UserResponse;
import by.it.medved.entities.User;
import by.it.medved.enums.Role;
import by.it.medved.exceptions.VerificationException;
import by.it.medved.mappers.UserMapper;
import by.it.medved.repositories.UserRepository;
import by.it.medved.services.EncryptionService;
import by.it.medved.services.TicketService;
import by.it.medved.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.it.medved.util.Message.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EncryptionService encryptionService;
    private final TicketService ticketService;

    @Override
    @Transactional
    public UserResponse saveUser(UserRequest userRequest) {
        if(checkUniqueLogin(userRequest.getLogin())) {
            User user = userMapper.mapToUser(userRequest);
            User savedUser = userRepository.save(user);
            return userMapper.mapToUserResponse(savedUser);
        } else {
            throw new VerificationException(format(LOGIN_BUSY_MESSAGE, userRequest.getLogin()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse authentication(AuthenticationRequest authenticationRequest) {
        User user = getUserByLogin(authenticationRequest.getLogin());
        User checkedUser = checkCurrentPassword(authenticationRequest.getPassword(), user);
        return userMapper.mapToUserResponse(checkedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::mapToUserResponse)
                .orElseThrow(() -> new EntityNotFoundException(format(USER_BY_ID_NOT_EXIST, id)));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format(USER_BY_ID_NOT_EXIST, id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToUserResponse)
                .toList();
    }

    @Override
    @Transactional
    public UserResponse changePassword(Long id, ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format(USER_BY_ID_NOT_EXIST, id)));
        String currentPassword = changePasswordRequest.getCurrentPassword();
        String newPassword = changePasswordRequest.getNewPassword();
        String repeatNewPassword = changePasswordRequest.getRepeatNewPassword();
        User checkedUser = checkCurrentPassword(currentPassword, user);
        if(checkPasswordsMatch(newPassword, repeatNewPassword)) {
            byte[] salt = checkedUser.getSalt();
            byte[] encryptedPassword = encryptionService.getEncryptedPassword(newPassword, salt);
            checkedUser.setPassword(encryptedPassword);
            User savedUser = userRepository.save(checkedUser);
            return userMapper.mapToUserResponse(savedUser);
        } else {
            throw new VerificationException(PASSWORDS_MISMATCH);
        }
    }

    @Override
    @Transactional
    public UserResponse updateRole(Long id, Role role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format(USER_BY_ID_NOT_EXIST, id)));
        user.setRole(role);
        User savedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(savedUser);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format(USER_BY_ID_NOT_EXIST, id)));
        User updateUser = updateFields(user, updateUserRequest);
        User savedUser = userRepository.save(updateUser);
        return userMapper.mapToUserResponse(savedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        ticketService.returnUserTickets(id);
        userRepository.deleteById(id);
    }

    private User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException(format(USER_BY_LOGIN_NOT_EXIST, login)));
    }

    private User updateFields(User user, UpdateUserRequest updateUserRequest) {
        if(updateUserRequest != null) {
            if(updateUserRequest.getFirstName() != null) {
                user.setFirstName(updateUserRequest.getFirstName());
            }
            if(updateUserRequest.getEmail() != null) {
                user.setEmail(updateUserRequest.getEmail());
            }
            if(updateUserRequest.getDateBirthday() != null) {
                user.setDateBirthday(updateUserRequest.getDateBirthday());
            }
        }
        return user;
    }

    private boolean checkUniqueLogin(String login) {
        return userRepository.findAll()
                .stream()
                .noneMatch(user -> user.getLogin().equalsIgnoreCase(login));
    }

    private User checkCurrentPassword(String currentPassword, User user) {
        if(encryptionService.authenticate(currentPassword, user.getPassword(), user.getSalt())) {
            return user;
        } else {
            throw new VerificationException(INCORRECT_PASSWORD);
        }
    }

    private boolean checkPasswordsMatch(String newPassword, String repeatNewPassword) {
        return newPassword.equals(repeatNewPassword);
    }
}
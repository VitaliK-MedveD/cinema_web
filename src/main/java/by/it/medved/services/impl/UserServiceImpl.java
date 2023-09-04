package by.it.medved.services.impl;

import by.it.medved.dto.request.UserRequest;
import by.it.medved.dto.response.UserResponse;
import by.it.medved.entities.User;
import by.it.medved.enums.Role;
import by.it.medved.mappers.UserMapper;
import by.it.medved.repositories.UserRepository;
import by.it.medved.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.it.medved.util.Message.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        User user = userMapper.mapToUser(userRequest);
        User savedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::mapToUserResponse)
                .orElseThrow(() -> new EntityNotFoundException(format(USER_BY_ID_NOT_EXIST, id)));
    }

    @Override
    public UserResponse getUserByLogin(String login) {
        return userRepository.findUserByLogin(login)
                .map(userMapper::mapToUserResponse)
                .orElseThrow(() -> new EntityNotFoundException(format(USER_BY_LOGIN_NOT_EXIST, login)));
    }

    @Override
    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToUserResponse)
                .toList();
    }

    @Override
    public UserResponse updateRole(Long id, Role role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format(USER_BY_ID_NOT_EXIST, id)));
        user.setRole(role);
        User savedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
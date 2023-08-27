package by.it.medved.services.impl;

import by.it.medved.config.MailProperties;
import by.it.medved.dto.UserRequest;
import by.it.medved.dto.UserResponse;
import by.it.medved.entities.User;
import by.it.medved.enums.Role;
import by.it.medved.mappers.UserMapper;
import by.it.medved.repositories.UserRepository;
import by.it.medved.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final MailProperties mailProperties;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        User savedUser = userRepository.save(userMapper.buildUser(userRequest));
        return userMapper.buildUserResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::buildUserResponse)
                .orElseThrow(() -> new EntityNotFoundException("user with id '" + id + "' does not exist"));
    }

    @Override
    public UserResponse getUserByLogin(String login) {
        return userRepository.findUserByLogin(login)
                .map(userMapper::buildUserResponse)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
    }

    @Override
    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::buildUserResponse)
                .toList();
    }

    @Override
    public UserResponse updateRole(Long id, Role role) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
        updateUser.setRole(role);
        User savedUser = userRepository.save(updateUser);
        return userMapper.buildUserResponse(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
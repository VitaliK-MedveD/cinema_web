package by.it.medved.services;

import by.it.medved.dto.request.AuthenticationRequest;
import by.it.medved.dto.request.ChangePasswordRequest;
import by.it.medved.dto.request.UpdateUserRequest;
import by.it.medved.dto.request.UserRequest;
import by.it.medved.dto.response.UserResponse;
import by.it.medved.entities.User;
import by.it.medved.enums.Role;

import java.util.List;

public interface UserService {

    UserResponse saveUser(UserRequest userRequest);

    UserResponse authentication(AuthenticationRequest authenticationRequest);

    UserResponse getUserById(Long id);

    public User getUser(Long id);

    List<UserResponse> getUsers();

    UserResponse changePassword(Long id, ChangePasswordRequest changePasswordRequest);

    UserResponse updateRole(Long id, Role role);

    UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest);

    void deleteUser(Long id);
}
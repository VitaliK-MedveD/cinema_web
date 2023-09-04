package by.it.medved.services;

import by.it.medved.dto.request.UserRequest;
import by.it.medved.dto.response.UserResponse;
import by.it.medved.enums.Role;

import java.util.List;

public interface UserService {

    UserResponse saveUser(UserRequest userRequest);

    UserResponse getUserById(Long id);

    UserResponse getUserByLogin(String login);

    List<UserResponse> getUsers();

    UserResponse updateRole(Long id, Role role);

    void deleteUser(Long id);
}
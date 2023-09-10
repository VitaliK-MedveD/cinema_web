package by.it.medved.controllers;

import by.it.medved.annotations.ExcludeLog;
import by.it.medved.dto.request.AuthenticationRequest;
import by.it.medved.dto.request.ChangePasswordRequest;
import by.it.medved.dto.request.UpdateUserRequest;
import by.it.medved.dto.request.UserRequest;
import by.it.medved.dto.response.UserResponse;
import by.it.medved.enums.Role;
import by.it.medved.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    @ExcludeLog
    @PostMapping("/user")
    public UserResponse saveUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }

    @ExcludeLog
    @GetMapping("/user")
    public UserResponse authentication(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return userService.authentication(authenticationRequest);
    }

    @GetMapping("/user/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @ExcludeLog
    @PatchMapping("/user/{id}")
    public UserResponse changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return userService.changePassword(id, changePasswordRequest);
    }

    @PatchMapping("/user/{id}/{role}")
    public UserResponse updateRole(@PathVariable Long id, @PathVariable Role role) {
        return userService.updateRole(id, role);
    }

    @PutMapping("/user/{id}")
    public UserResponse updateFields(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(id, updateUserRequest);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
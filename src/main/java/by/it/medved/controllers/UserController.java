package by.it.medved.controllers;

import by.it.medved.annotations.ExcludeLog;
import by.it.medved.dto.request.AuthenticationRequest;
import by.it.medved.dto.request.ChangePasswordRequest;
import by.it.medved.dto.request.UpdateUserRequest;
import by.it.medved.dto.request.UserRequest;
import by.it.medved.dto.response.UserResponse;
import by.it.medved.enums.Role;
import by.it.medved.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Tag(name = "User Controller", description = "Responsible for handling queries and managing user-related activities")
public class UserController {

    private final UserService userService;

    @ExcludeLog
    @PostMapping("/user")
    @Operation(description = "Saving user to the database")
    public UserResponse saveUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }

    @ExcludeLog
    @GetMapping("/user")
    @Operation(description = "User authentication")
    public UserResponse authentication(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return userService.authentication(authenticationRequest);
    }

    @GetMapping("/user/{id}")
    @Operation(description = "Getting user by ID")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    @Operation(description = "Getting all users")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @ExcludeLog
    @PatchMapping("/user/{id}")
    @Operation(description = "User password change")
    public UserResponse changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return userService.changePassword(id, changePasswordRequest);
    }

    @PatchMapping("/user/{id}/{role}")
    @Operation(description = "Update user role")
    public UserResponse updateRole(@PathVariable Long id, @PathVariable Role role) {
        return userService.updateRole(id, role);
    }

    @PutMapping("/user/{id}")
    @Operation(description = "Update user profile")
    public UserResponse updateFields(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(id, updateUserRequest);
    }

    @DeleteMapping("/user/{id}")
    @Operation(description = "Delete user by ID")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
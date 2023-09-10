package by.it.medved.services.impl;

import by.it.medved.dto.request.AuthenticationRequest;
import by.it.medved.dto.request.ChangePasswordRequest;
import by.it.medved.dto.request.UpdateUserRequest;
import by.it.medved.dto.request.UserRequest;
import by.it.medved.dto.response.UserResponse;
import by.it.medved.entities.User;
import by.it.medved.enums.Role;
import by.it.medved.mappers.UserMapper;
import by.it.medved.repositories.UserRepository;
import by.it.medved.services.EncryptionService;
import by.it.medved.services.TicketService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("UserService tests")
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private static final Random RANDOM = new Random();

    @Mock
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EncryptionService encryptionService;
    @Mock
    private TicketService ticketService;
    @InjectMocks
    private UserServiceImpl userService;
    private static UserRequest userRequest;
    private static AuthenticationRequest authenticationRequest;
    private static ChangePasswordRequest changePasswordRequest;
    private static UpdateUserRequest updateUserRequest;

    @BeforeAll
    static void beforeAll() {
        userRequest = UserRequest.builder()
                .login("Test")
                .password("123456")
                .firstName("TestName")
                .email("test@test.by")
                .dateBirthday(LocalDate.of(2000, 1, 1))
                .build();
        authenticationRequest = AuthenticationRequest.builder()
                .login("Test")
                .password("123456")
                .build();
        changePasswordRequest = ChangePasswordRequest.builder()
                .currentPassword("123456")
                .newPassword("654321")
                .repeatNewPassword("654321")
                .build();
        updateUserRequest = UpdateUserRequest.builder()
                .firstName("TestName2")
                .email("test2@test.by")
                .dateBirthday(LocalDate.of(1999, 1, 1))
                .build();
    }

    @BeforeEach
    void init() {
        userService = new UserServiceImpl(userRepository, userMapper, encryptionService, ticketService);
    }

    @Test
    @DisplayName("Authentication test")
    void authenticationTest() {
        when(userRepository.findUserByLogin(authenticationRequest.getLogin())).thenReturn(Optional.of(getUser()));
        UserResponse response = userService.authentication(authenticationRequest);
        assertNotNull(response);
        assertEquals(authenticationRequest.getLogin(), response.getLogin());
    }

    @Test
    @DisplayName("Get user test by id")
    void getUserByIdTest() {
        User user = getUser();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        UserResponse response = userService.getUserById(user.getId());
        assertNotNull(response);
        assertEquals(user.getId(), response.getId());
        verify(userRepository).findById(user.getId());
    }

    @Test
    @DisplayName("Get users test")
    void getUsersTest() {
        List<User> users = getUsers();
        when(userRepository.findAll()).thenReturn(users);
        List<UserResponse> responses = userService.getUsers();
        assertNotNull(responses);
        assertEquals(3, responses.size());
        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("Password change test")
    void changePasswordTest() {
        User user = getUser();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        UserResponse response = userService.changePassword(user.getId(), changePasswordRequest);
        assertNotNull(response);
        assertEquals(user.getLogin(), response.getLogin());
        verify(userRepository).findById(user.getId());
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("Role update test")
    void updateRoleTest() {
        User user = getUser();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        UserResponse response = userService.updateRole(user.getId(), Role.MANAGER);
        assertNotNull(response);
        assertEquals(Role.MANAGER, response.getRole());
    }

    @Test
    @DisplayName("User profile update test")
    void updateUserTest() {
        User user = getUser();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        UserResponse response = userService.updateUser(user.getId(), updateUserRequest);
        assertNotNull(response);
        assertEquals(updateUserRequest.getFirstName(), response.getFirstName());
        assertEquals(updateUserRequest.getEmail(), response.getEmail());
    }

    private User getUser() {
        User user = userMapper.mapToUser(userRequest);
        user.setId(RANDOM.nextLong());
        return user;
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            users.add(getUser());
        }
        return users;
    }
}
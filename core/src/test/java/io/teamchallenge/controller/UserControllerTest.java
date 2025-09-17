package io.teamchallenge.controller;

import io.teamchallenge.dto.user.UserProfile;
import io.teamchallenge.enumerated.Role;
import io.teamchallenge.exception.NotFoundException;
import io.teamchallenge.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

import static io.teamchallenge.constant.ExceptionMessage.USER_NOT_FOUND_BY_EMAIL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Mock
    private Principal principal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserProfile_UserExists_ReturnsUserVO() {
        // Arrange
        String email = "test@example.com";
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setFullName("John");
        userProfile.setEmail(email);
        userProfile.setRole(Role.valueOf("ROLE_USER"));

        when(principal.getName()).thenReturn(email);
        when(userService.getUserProfile(email)).thenReturn(userProfile);

        // Act
        ResponseEntity<UserProfile> response = userController.getUserProfile(principal);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userProfile, response.getBody());
    }

    @Test
    void getUserProfile_UserDoesNotExist_ThrowsNotFoundException() {
        // Arrange
        String email = "nonexistent@example.com";

        when(principal.getName()).thenReturn(email);
        when(userService.getUserProfile(email)).thenThrow(new NotFoundException(USER_NOT_FOUND_BY_EMAIL.formatted(email)));

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            userController.getUserProfile(principal);
        });

        assertEquals("There is no user with email: " + email, exception.getMessage());
    }
}

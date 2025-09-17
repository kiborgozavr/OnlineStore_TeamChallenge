package io.teamchallenge.service.impl;

import io.teamchallenge.dto.user.UserProfile;
import io.teamchallenge.entity.Address;
import io.teamchallenge.entity.User;
import io.teamchallenge.enumerated.Role;
import io.teamchallenge.exception.ConflictException;
import io.teamchallenge.exception.NotFoundException;
import io.teamchallenge.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserProfile_UserExists_ReturnsUserVO() {
        // Arrange
        String email = "test@example.com";
        User user = new User();
        user.setId(1L);
        user.setFullName("John");
        user.setEmail(email);
        user.setRole(Role.valueOf("ROLE_USER"));

        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setFullName("John");
        userProfile.setEmail(email);
        userProfile.setRole(Role.valueOf("ROLE_USER"));

        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserProfile.class)).thenReturn(userProfile);

        // Act
        UserProfile result = userService.getUserProfile(email);

        // Assert
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getFullName(), result.getFullName());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getRole(), result.getRole());
    }

    @Test
    void getUserProfile_UserDoesNotExist_ThrowsNotFoundException() {
        // Arrange
        String email = "nonexistent@example.com";

        when(userRepository.findUserByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            userService.getUserProfile(email);
        });

        assertEquals("There is no user with email: " + email, exception.getMessage());
    }

    @Test
    void updateUserProfile_UserExists_UpdatesAndReturnsUserProfile() {
        // Arrange
        String email = "test@example.com";
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setFullName("John Doe");
        user.setEmail(email);
        user.setRole(Role.ROLE_USER);
        user.setAddress(new Address());

        UserProfile userProfile = new UserProfile();
        userProfile.setFullName("John Updated");
        userProfile.setEmail(email);
        userProfile.setRole(Role.ROLE_USER);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.existsOtherUserWithThisEmail(id, email)).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, UserProfile.class)).thenReturn(userProfile);
        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(user));

        // Act
        UserProfile result = userService.updateUserProfile(id, userProfile);

        // Assert
        assertNotNull(result);
        assertEquals(userProfile.getFullName(), result.getFullName());
        assertEquals(userProfile.getEmail(), result.getEmail());
        assertEquals(userProfile.getRole(), result.getRole());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUserProfile_UserDoesNotExist_ThrowsNotFoundException() {
        // Arrange
        Long id = 1L;
        UserProfile userProfile = new UserProfile();

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            userService.updateUserProfile(id, userProfile);
        });

        assertEquals("There is no user with id: " + id, exception.getMessage());
    }

    @Test
    void updateUserProfile_EmailConflict_ThrowsConflictException() {
        // Arrange
        String email = "test@example.com";
        String newEmail = "new@example.com";
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setFullName("John Doe");
        user.setEmail(email);
        user.setRole(Role.ROLE_USER);

        UserProfile userProfile = new UserProfile();
        userProfile.setFullName("John Updated");
        userProfile.setEmail(newEmail);
        userProfile.setRole(Role.ROLE_USER);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.existsOtherUserWithThisEmail(id, newEmail)).thenReturn(true);
        when(modelMapper.map(user, UserProfile.class)).thenReturn(userProfile);

        // Act & Assert
        ConflictException exception = assertThrows(ConflictException.class, () -> {
            userService.updateUserProfile(id, userProfile);
        });

        assertEquals("User with email " + newEmail + " already exists", exception.getMessage());
    }

}

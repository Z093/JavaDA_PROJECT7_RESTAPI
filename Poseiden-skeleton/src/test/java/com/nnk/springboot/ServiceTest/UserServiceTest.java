package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void findAll_shouldReturnListOfUsers() {
        // Given
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("user1");

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("user2");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // When
        List<User> users = userService.findAll();

        // Then
        assertThat(users).hasSize(2).containsExactly(user1, user2);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void save_shouldReturnSavedUser() {
        // Given
        User user = new User();
        user.setUsername("savedUser");

        when(userRepository.save(user)).thenReturn(user);

        // When
        User saved = userService.save(user);

        // Then
        assertThat(saved).isEqualTo(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void findById_shouldReturnUserWhenExists() {
        // Given
        User user = new User();
        user.setId(42);
        when(userRepository.findById(42)).thenReturn(Optional.of(user));

        // When
        Optional<User> result = userService.findById(42);

        // Then
        assertThat(result).isPresent().contains(user);
        verify(userRepository).findById(42);
    }

    @Test
    void deleteById_shouldInvokeRepositoryDelete() {
        // When
        userService.deleteById(10);

        // Then
        verify(userRepository, times(1)).deleteById(10);
    }
}

/*
package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void userCrudTest() {
        // Clean up any test user if exists
        userRepository.findByUsername("testuser").ifPresent(u -> userRepository.delete(u));

        // Create User
        User user = new User();
        user.setFullname("Test User");
        user.setUsername("testuser");
        user.setPassword("Password1@"); // Raw password
        user.setRole("USER");

        // Save through service
        User savedUser = userService.saveUser(user);
        Assert.assertNotNull(savedUser.getId());
        Assert.assertEquals("testuser", savedUser.getUsername());

        // Password should be encoded
        Assert.assertTrue(passwordEncoder.matches("Password1@", savedUser.getPassword()));
        Assert.assertNotEquals("Password1@", savedUser.getPassword());

        // Find user
        Optional<User> foundUser = userService.findById(savedUser.getId());
        Assert.assertTrue(foundUser.isPresent());
        Assert.assertEquals("testuser", foundUser.get().getUsername());

        // Update user
        User userToUpdate = foundUser.get();
        userToUpdate.setFullname("Updated User");
        User updatedUser = userService.saveUser(userToUpdate);
        Assert.assertEquals("Updated User", updatedUser.getFullname());

        // Find all users
        List<User> users = userService.findAllUsers();
        Assert.assertTrue(users.size() > 0);

        // Delete user
        userService.deleteUser(updatedUser.getId());
        Assert.assertFalse(userService.findById(updatedUser.getId()).isPresent());
    }
}

*/

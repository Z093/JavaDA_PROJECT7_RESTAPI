/*
package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserDetailsServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsServiceTests {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User testUser;

    @Before
    public void setUp() {
        // Create a test user for authentication
        testUser = new User();
        testUser.setFullname("Security Test User");
        testUser.setUsername("securitytest");
        testUser.setPassword(passwordEncoder.encode("Test1234!"));
        testUser.setRole("USER");

        userRepository.save(testUser);
    }

    @After
    public void cleanUp() {
        // Clean up after test
        userRepository.delete(testUser);
    }

    @Test
    public void loadUserByUsername_ValidUser_ReturnsUserDetails() {
        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername("securitytest");

        // Assert
        assertNotNull(userDetails);
        assertEquals("securitytest", userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_InvalidUser_ThrowsException() {
        // Act - should throw exception
        userDetailsService.loadUserByUsername("nonexistentuser");
    }
}
*/

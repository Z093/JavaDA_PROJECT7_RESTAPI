/*
package com.nnk.springboot.config;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

*/
/**
 * Initializes the database with sample data when the application starts.
 *//*

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = Logger.getLogger(DataInitializer.class.getName());

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        logger.info("Starting data initialization...");

        // Add an admin user if not exists
        if (!userRepository.findByUsername("admin").isPresent()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("AdminPass123!"));
            admin.setFullname("Administrator");
            admin.setRole("ADMIN");
            userRepository.save(admin);
            logger.info("Admin user has been created.");
        }

        // Add a regular user if not exists
        if (!userRepository.findByUsername("user").isPresent()) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("UserPass123!"));
            user.setFullname("Regular User");
            user.setRole("USER");
            userRepository.save(user);
            logger.info("Regular user has been created.");
        }

        logger.info("Data initialization completed.");
    }
}
*/

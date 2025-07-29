package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean validateLogin(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    public boolean registerUser(String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            return false; // user already exists
        }
        userRepository.save(new User(email, password));
        return true;
    }

    // For testing: seed a default user
    public void registerTestUser() {
        if (userRepository.findByEmail("user@example.com").isEmpty()) {
            userRepository.save(new User("user@example.com", "password123"));
        }
    }
}

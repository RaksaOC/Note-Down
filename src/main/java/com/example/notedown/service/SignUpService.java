package com.example.notedown.service;

import com.example.notedown.model.User;
import com.example.notedown.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String register(String username, String password) {
        User user = new User(username, passwordEncoder.encode(password));
        userRepository.save(user);
        return user.getUsername() + "has been created";
    }
}
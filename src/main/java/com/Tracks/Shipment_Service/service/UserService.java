package com.Tracks.Shipment_Service.service;

import com.Tracks.Shipment_Service.entity.User;
import com.Tracks.Shipment_Service.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(String email, String password) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new RuntimeException("User with email " + email + " already exists!");
        });
        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .role("USER")
                .build();
        return userRepository.save(user);
    }

    public User load(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Runtime Error" + email));
        System.out.println(user);
        return user;
    }
}
 //user entity -> userservice -> repo ->userrequest->config-> controller
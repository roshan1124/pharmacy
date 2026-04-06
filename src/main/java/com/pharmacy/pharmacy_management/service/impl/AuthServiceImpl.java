package com.pharmacy.pharmacy_management.service.impl;

import com.pharmacy.pharmacy_management.dto.request.LoginRequest;
import com.pharmacy.pharmacy_management.dto.request.RegisterRequest;
import com.pharmacy.pharmacy_management.dto.response.AuthResponse;
import com.pharmacy.pharmacy_management.entity.Role;
import com.pharmacy.pharmacy_management.entity.User;
import com.pharmacy.pharmacy_management.repository.UserRepository;
import com.pharmacy.pharmacy_management.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return new AuthResponse(
                "temp-token",
                user.getUsername(),
                user.getRole().toString(),
                "Login successful"
        );
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {

        // Check if username already exists
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // Convert role from String to Enum
        Role role;
        if (registerRequest.getRole().equalsIgnoreCase("ADMIN")) {
            role = Role.ADMIN;
        } else {
            role = Role.STAFF;
        }

        // Create new user
        User user = new User(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getFullName(),
                role
        );

        userRepository.save(user);

        return new AuthResponse(
                "temp-token",
                user.getUsername(),
                user.getRole().toString(),
                "Registration successful"
        );
    }
}
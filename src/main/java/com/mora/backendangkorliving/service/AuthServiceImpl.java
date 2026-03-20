package com.mora.backendangkorliving.service;

import com.mora.backendangkorliving.DTOs.request.LoginRequest;
import com.mora.backendangkorliving.DTOs.request.RegisterRequest;
import com.mora.backendangkorliving.DTOs.response.AuthResponse;
import com.mora.backendangkorliving.model.Role;
import com.mora.backendangkorliving.model.User;
import com.mora.backendangkorliving.Repository.UserRepository;
import com.mora.backendangkorliving.security.JwtUtil;
import com.mora.backendangkorliving.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .role(
                        request.getRole() != null
                                ? request.getRole()
                                : Role.TENANT // default role
                )
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token, user.getRole().name());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token, user.getRole().name());
    }
}


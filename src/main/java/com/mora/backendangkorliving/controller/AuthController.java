package com.mora.backendangkorliving.controller;


import com.mora.backendangkorliving.DTOs.request.LoginRequest;
import com.mora.backendangkorliving.DTOs.request.RegisterRequest;
import com.mora.backendangkorliving.DTOs.response.AuthResponse;
import com.mora.backendangkorliving.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}


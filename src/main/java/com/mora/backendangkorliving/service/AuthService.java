package com.mora.backendangkorliving.service;


import com.mora.backendangkorliving.DTOs.request.LoginRequest;
import com.mora.backendangkorliving.DTOs.request.RegisterRequest;
import com.mora.backendangkorliving.DTOs.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}


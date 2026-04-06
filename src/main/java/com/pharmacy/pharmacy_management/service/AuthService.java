package com.pharmacy.pharmacy_management.service;

import com.pharmacy.pharmacy_management.dto.request.LoginRequest;
import com.pharmacy.pharmacy_management.dto.request.RegisterRequest;
import com.pharmacy.pharmacy_management.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(RegisterRequest registerRequest);
}
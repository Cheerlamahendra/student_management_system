package com.student_management_system.service;

import com.student_management_system.dto.JwtResponse;
import com.student_management_system.dto.LoginRequest;
import com.student_management_system.dto.RegisterRequest;

public interface AuthService
{

    String register(RegisterRequest request);

    JwtResponse login(LoginRequest request);
}

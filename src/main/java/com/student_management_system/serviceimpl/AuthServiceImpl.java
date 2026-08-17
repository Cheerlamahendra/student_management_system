package com.student_management_system.serviceimpl;

import com.student_management_system.dto.JwtResponse;
import com.student_management_system.dto.LoginRequest;
import com.student_management_system.dto.RegisterRequest;
import com.student_management_system.util.Role;
import com.student_management_system.entity.Users;
import com.student_management_system.repository.UserRepository;
import com.student_management_system.security.CustomUserDetails;
import com.student_management_system.security.JwtService;
import com.student_management_system.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Users user = Users.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() == null ? Role.ROLE_USER : request.getRole())
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public JwtResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Users user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        String token = jwtService.generateToken(new CustomUserDetails(user));

        return JwtResponse.builder()
                .token(token)
                .type("Bearer")
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

}

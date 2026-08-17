package com.student_management_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse
{
    private String token;
    private String type;
    private String email;
    private String role;
}

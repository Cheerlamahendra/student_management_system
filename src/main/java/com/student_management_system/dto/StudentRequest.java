package com.student_management_system.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentRequest
{

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid Email")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain 10 digits")
    private String phone;

    @NotBlank(message = "Department is required")
    private String department;

    @Min(1)
    @Max(4)
    private Integer year;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private Double cgpa;

    private String address;

}

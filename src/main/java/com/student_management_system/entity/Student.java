package com.student_management_system.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain 10 digits")
    private String phone;

    @NotBlank(message = "Department is required")
    private String department;

    @Min(value = 1, message = "Year must be at least 1")
    @Max(value = 4, message = "Year must not exceed 4")
    private Integer year;

    @DecimalMin(value = "0.0", message = "CGPA cannot be negative")
    @DecimalMax(value = "10.0", message = "CGPA cannot exceed 10")
    private Double cgpa;

    private String address;

}

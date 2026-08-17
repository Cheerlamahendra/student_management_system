package com.student_management_system.dto;


import lombok.Data;

@Data
public class StudentResponse
{
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String department;
    private Integer year;
    private Double cgpa;
    private String address;
}

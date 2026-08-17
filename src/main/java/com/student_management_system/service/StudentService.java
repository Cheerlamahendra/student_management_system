package com.student_management_system.service;

import com.student_management_system.dto.StudentRequest;
import com.student_management_system.dto.StudentResponse;
import org.springframework.data.domain.Page;


public interface StudentService
{
    StudentResponse addStudent(StudentRequest request);

    StudentResponse getStudentById(Long id);

    Page<StudentResponse> getAllStudents(int page, int size, String sortBy);

    StudentResponse updateStudent(Long id, StudentRequest request);

    void deleteStudent(Long id);

    Page<StudentResponse> searchStudents(String name, int page, int size);
}

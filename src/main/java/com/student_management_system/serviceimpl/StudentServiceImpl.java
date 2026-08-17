package com.student_management_system.serviceimpl;

import com.student_management_system.dto.StudentRequest;
import com.student_management_system.dto.StudentResponse;
import com.student_management_system.entity.Student;
import com.student_management_system.exception.ResourceNotFoundException;
import com.student_management_system.repository.StudentRepository;
import com.student_management_system.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentResponse addStudent(StudentRequest request) {

        Student student = new Student();

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setDepartment(request.getDepartment());
        student.setYear(request.getYear());
        student.setCgpa(request.getCgpa());
        student.setAddress(request.getAddress());

        Student savedStudent = studentRepository.save(student);

        return convertToResponse(savedStudent);
    }

    @Override
    public StudentResponse getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id));

        return convertToResponse(student);
    }

    @Override
    public Page<StudentResponse> getAllStudents(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return studentRepository.findAll(pageable)
                .map(this::convertToResponse);
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id));

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setDepartment(request.getDepartment());
        student.setYear(request.getYear());
        student.setCgpa(request.getCgpa());
        student.setAddress(request.getAddress());

        Student updatedStudent = studentRepository.save(student);

        return convertToResponse(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id));

        studentRepository.delete(student);
    }

    @Override
    public Page<StudentResponse> searchStudents(String name, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return studentRepository
                .findByNameContainingIgnoreCase(name, pageable)
                .map(this::convertToResponse);
    }

    private StudentResponse convertToResponse(Student student) {

        StudentResponse response = new StudentResponse();

        response.setId(student.getId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setPhone(student.getPhone());
        response.setDepartment(student.getDepartment());
        response.setYear(student.getYear());
        response.setCgpa(student.getCgpa());
        response.setAddress(student.getAddress());

        return response;
    }
}
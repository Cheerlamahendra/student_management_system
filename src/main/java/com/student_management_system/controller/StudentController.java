package com.student_management_system.controller;


import com.student_management_system.dto.StudentRequest;
import com.student_management_system.dto.StudentResponse;
import com.student_management_system.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController
{

    private final StudentService studentService;

    // Add Student
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentResponse> addStudent(
            @Valid @RequestBody StudentRequest request) {

        return new ResponseEntity<>(
                studentService.addStudent(request),
                HttpStatus.CREATED
        );
    }

    // Get Student By ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<StudentResponse> getStudentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // Get All Students
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Page<StudentResponse>> getAllStudents(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sortBy) {

        return ResponseEntity.ok(
                studentService.getAllStudents(page, size, sortBy)
        );
    }

    // Update Student
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentResponse> updateStudent(

            @PathVariable Long id,

            @Valid @RequestBody StudentRequest request) {

        return ResponseEntity.ok(
                studentService.updateStudent(id, request)
        );
    }

    // Delete Student
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok("Student Deleted Successfully");
    }

    // Search Student
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")

    public ResponseEntity<Page<StudentResponse>> searchStudents(

            @RequestParam String name,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(
                studentService.searchStudents(name, page, size)
        );
    }
}

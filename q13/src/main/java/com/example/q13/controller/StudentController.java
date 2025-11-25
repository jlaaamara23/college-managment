package com.example.q13.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.example.q13.dto.StudentCreateDto;
import com.example.q13.dto.StudentDto;
import com.example.q13.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // POST:
    @PostMapping
    public StudentDto createStudent(@RequestBody StudentCreateDto dto) {
        StudentDto newStudent = new StudentDto();
        newStudent.setFirstname(dto.getFirstname());
        newStudent.setLastname(dto.getLastname());
        newStudent.setEmail(dto.getEmail());
        newStudent.setDepartment(dto.getDepartment());
        newStudent.setGpa(dto.getGpa());
        newStudent.setYear(dto.getYear());
        return studentService.createStudent(newStudent);
    }

    // GET: 
    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    // GET:
    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // GET: 
    @GetMapping("/department/{department}")
    public List<StudentDto> getStudentsByDepartment(@PathVariable String department) {
        return studentService
                .getAllStudents()
                .stream()
                .filter(s -> s.getDepartment().equalsIgnoreCase(department))
                .toList();
    }
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}

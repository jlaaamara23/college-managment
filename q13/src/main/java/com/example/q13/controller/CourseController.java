package com.example.q13.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.q13.dto.CourseCreateDto;
import com.example.q13.dto.CourseDto;
import com.example.q13.service.CourseService;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public CourseDto createCourse(@RequestBody CourseCreateDto dto) {
        return courseService.createCourse(dto);
    }

    @GetMapping
    public List<CourseDto> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDto getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("/{id}")
    public CourseDto updateCourse(@PathVariable Long id, @RequestBody CourseCreateDto dto) {
        return courseService.updateCourse(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/instructor/{instructorId}")
    public List<CourseDto> getCoursesByInstructor(@PathVariable Long instructorId) {
        return courseService.getCoursesByInstructor(instructorId);
    }
}

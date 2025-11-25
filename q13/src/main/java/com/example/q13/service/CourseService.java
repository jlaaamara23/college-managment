package com.example.q13.service;

import com.example.q13.dto.CourseCreateDto;
import com.example.q13.dto.CourseDto;
import com.example.q13.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private List<CourseDto> courses = new ArrayList<>();
    private Long nextId = 1L;

    public CourseDto createCourse(CourseCreateDto dto) {
        CourseDto course = new CourseDto();
        course.setId(nextId++);
        course.setName(dto.getName());
        course.setCourseCode(dto.getCourseCode());
        course.setCredits(dto.getCredits());
        course.setInstructorId(dto.getInstructorId());

        courses.add(course);
        return course;
    }

    public List<CourseDto> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public CourseDto getCourseById(Long id) {
        return courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Course not found: " + id));
    }

    public CourseDto updateCourse(Long id, CourseCreateDto dto) {
        CourseDto c = getCourseById(id);

        c.setName(dto.getName());
        c.setCourseCode(dto.getCourseCode());
        c.setCredits(dto.getCredits());
        c.setInstructorId(dto.getInstructorId());

        return c;
    }

    public void deleteCourse(Long id) {
        CourseDto c = getCourseById(id);
        courses.remove(c);
    }

    public List<CourseDto> getCoursesByInstructor(Long instructorId) {
        return courses.stream()
                .filter(c -> c.getInstructorId().equals(instructorId))
                .toList();
    }
}

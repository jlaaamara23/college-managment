package com.example.q13.dto;

public class CourseDto {
    private Long id;
    private String name;
    private String courseCode;
    private int credits;
    private Long instructorId;

    public CourseDto() {}

    public CourseDto(Long id, String name, String courseCode, int credits, Long instructorId) {
        this.id = id;
        this.name = name;
        this.courseCode = courseCode;
        this.credits = credits;
        this.instructorId = instructorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }
}

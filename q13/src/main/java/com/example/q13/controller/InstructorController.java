package com.example.q13.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.q13.dto.*;
import com.example.q13.service.InstructorService;

@RestController
@RequestMapping("/api/instructors")
@CrossOrigin(origins = "http://localhost:5173")
public class InstructorController {

    private final List<InstructorDto> instructors = List.of(
            new InstructorDto(1L, "כהן", "אבי", "avi.cohen@college.ac.il", "מדעי המחשב", "ד\"ר"),
            new InstructorDto(2L, "לוי", "דנה", "dana.levi@college.ac.il", "הנדסת תוכנה", "ד\"ר"),
            new InstructorDto(3L, "מזרחי", "רון", "ron.mizrahi@college.ac.il", "מדעי המחשב", "פרופ׳"),
            new InstructorDto(4L, "אבוטבול", "מיכל", "michal.a@college.ac.il", "מערכות מידע", "ד\"ר")
    );

    @GetMapping
    public List<InstructorDto> getAllInstructors() {
        return instructors;
    }
}

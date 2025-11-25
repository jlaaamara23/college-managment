package com.example.q13.controller;

import com.example.q13.dto.DoctorCreateDto;
import com.example.q13.dto.DoctorDto;
import com.example.q13.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {
    
    private final DoctorService doctorService;
    
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    
    @PostMapping
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorCreateDto dto) {
        DoctorDto createdDoctor = doctorService.createDoctor(dto);
        return ResponseEntity.ok(createdDoctor);
    }
    
    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        List<DoctorDto> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long id) {
        DoctorDto doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable Long id, @RequestBody DoctorCreateDto dto) {
        DoctorDto updatedDoctor = doctorService.updateDoctor(id, dto);
        return ResponseEntity.ok(updatedDoctor);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
    
    // Advanced endpoints
    @GetMapping("/by-specialization-map")
    public ResponseEntity<Map<String, List<DoctorDto>>> getDoctorsBySpecializationMap() {
        Map<String, List<DoctorDto>> doctorsBySpecialization = doctorService.getDoctorsBySpecializationMap();
        return ResponseEntity.ok(doctorsBySpecialization);
    }
    
    @GetMapping("/top-rated")
    public ResponseEntity<List<DoctorDto>> getTopRatedDoctors(@RequestParam int limit) {
        List<DoctorDto> topDoctors = doctorService.getTopRatedDoctors(limit);
        return ResponseEntity.ok(topDoctors);
    }
    
    @GetMapping("/experience-range")
    public ResponseEntity<List<DoctorDto>> getDoctorsByExperienceRange(
            @RequestParam int min, 
            @RequestParam int max) {
        List<DoctorDto> doctors = doctorService.getDoctorsByExperienceRange(min, max);
        return ResponseEntity.ok(doctors);
    }
    
    @GetMapping("/average-rating-by-spec")
    public ResponseEntity<Map<String, Double>> getAverageRatingBySpecialization() {
        Map<String, Double> averageRatings = doctorService.getAverageRatingBySpecialization();
        return ResponseEntity.ok(averageRatings);
    }
    
    @GetMapping("/most-appointments")
    public ResponseEntity<List<DoctorDto>> getDoctorsWithMostAppointments(@RequestParam int limit) {
        List<DoctorDto> doctors = doctorService.getDoctorsWithMostAppointments(limit);
        return ResponseEntity.ok(doctors);
    }
}
package com.example.q13.controller;

import com.example.q13.dto.PatientCreateDto;
import com.example.q13.dto.PatientDto;
import com.example.q13.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {
    
    private final PatientService patientService;
    
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    
    // Basic endpoints
    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientCreateDto dto) {
        PatientDto createdPatient = patientService.createPatient(dto);
        return ResponseEntity.ok(createdPatient);
    }
    
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
        PatientDto patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @RequestBody PatientCreateDto dto) {
        PatientDto updatedPatient = patientService.updatePatient(id, dto);
        return ResponseEntity.ok(updatedPatient);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
    
    // Advanced endpoints (as provided originally)
    @GetMapping("/age-range")
    public ResponseEntity<List<PatientDto>> getPatientsByAgeRange(
            @RequestParam int min, 
            @RequestParam int max) {
        List<PatientDto> patients = patientService.getPatientsByAgeRange(min, max);
        return ResponseEntity.ok(patients);
    }
    
    @GetMapping("/age-statistics")
    public ResponseEntity<Map<String, Object>> getPatientAgeStatistics() {
        Map<String, Object> statistics = patientService.getPatientAgeStatistics();
        return ResponseEntity.ok(statistics);
    }
    
    @GetMapping("/grouped-by-age")
    public ResponseEntity<Map<String, List<PatientDto>>> getPatientsGroupedByAgeGroup() {
        Map<String, List<PatientDto>> patientsByAgeGroup = patientService.getPatientsGroupedByAgeGroup();
        return ResponseEntity.ok(patientsByAgeGroup);
    }
    
    @GetMapping("/most-appointments")
    public ResponseEntity<List<PatientDto>> getPatientsWithMostAppointments(@RequestParam int limit) {
        List<PatientDto> patients = patientService.getPatientsWithMostAppointments(limit);
        return ResponseEntity.ok(patients);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PatientDto>> searchPatientsByName(@RequestParam String keyword) {
        List<PatientDto> patients = patientService.searchPatientsByName(keyword);
        return ResponseEntity.ok(patients);
    }
}
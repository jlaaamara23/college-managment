package com.example.q13.controller;

import com.example.q13.dto.AppointmentCreateDto;
import com.example.q13.dto.AppointmentDto;
import com.example.q13.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {
    
    private final AppointmentService appointmentService;
    
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    
    // Basic endpoints
    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody AppointmentCreateDto dto) {
        AppointmentDto createdAppointment = appointmentService.createAppointment(dto);
        return ResponseEntity.ok(createdAppointment);
    }
    
    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        List<AppointmentDto> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable Long id) {
        AppointmentDto appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> updateAppointment(@PathVariable Long id, @RequestBody AppointmentCreateDto dto) {
        AppointmentDto updatedAppointment = appointmentService.updateAppointment(id, dto);
        return ResponseEntity.ok(updatedAppointment);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}/cancel")
    public ResponseEntity<AppointmentDto> cancelAppointment(@PathVariable Long id) {
        AppointmentDto appointment = appointmentService.getAppointmentById(id);
        appointment.setStatus("CANCELLED");
        return ResponseEntity.ok(appointment);
    }
    
    @PutMapping("/{id}/complete")
    public ResponseEntity<AppointmentDto> completeAppointment(@PathVariable Long id) {
        AppointmentDto appointment = appointmentService.getAppointmentById(id);
        appointment.setStatus("COMPLETED");
        return ResponseEntity.ok(appointment);
    }
    
    // Advanced endpoints
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByPriority(@PathVariable String priority) {
        List<AppointmentDto> appointments = appointmentService.getAppointmentsByPriority(priority);
        return ResponseEntity.ok(appointments);
    }
    
    @GetMapping("/upcoming")
    public ResponseEntity<List<AppointmentDto>> getUpcomingAppointments(@RequestParam int days) {
        List<AppointmentDto> appointments = appointmentService.getUpcomingAppointments(days);
        return ResponseEntity.ok(appointments);
    }
    
    @GetMapping("/statistics-by-status")
    public ResponseEntity<Map<String, Long>> getAppointmentStatisticsByStatus() {
        Map<String, Long> statistics = appointmentService.getAppointmentStatisticsByStatus();
        return ResponseEntity.ok(statistics);
    }
    
    @GetMapping("/date-range")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByDateRange(
            @RequestParam String start, 
            @RequestParam String end) {
        List<AppointmentDto> appointments = appointmentService.getAppointmentsByDateRange(start, end);
        return ResponseEntity.ok(appointments);
    }
    
    @GetMapping("/daily-schedule/{doctorId}")
    public ResponseEntity<List<AppointmentDto>> getDailySchedule(
            @PathVariable Long doctorId, 
            @RequestParam String date) {
        List<AppointmentDto> appointments = appointmentService.getDailySchedule(doctorId, date);
        return ResponseEntity.ok(appointments);
    }
    
    @GetMapping("/available-slots/{doctorId}")
    public ResponseEntity<List<String>> getAvailableTimeSlots(
            @PathVariable Long doctorId, 
            @RequestParam String date) {
        List<String> availableSlots = appointmentService.getAvailableTimeSlots(doctorId, date);
        return ResponseEntity.ok(availableSlots);
    }
    
    @GetMapping("/most-busy-day")
    public ResponseEntity<String> getMostBusyDay() {
        String busiestDay = appointmentService.getMostBusyDay();
        return ResponseEntity.ok(busiestDay);
    }
    
    @GetMapping("/duration-range")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByDurationRange(
            @RequestParam int min, 
            @RequestParam int max) {
        List<AppointmentDto> appointments = appointmentService.getAppointmentsByDurationRange(min, max);
        return ResponseEntity.ok(appointments);
    }
    
    @GetMapping("/cancelled-report")
    public ResponseEntity<Map<String, Object>> getCancelledAppointmentsReport() {
        Map<String, Object> report = appointmentService.getCancelledAppointmentsReport();
        return ResponseEntity.ok(report);
    }
}
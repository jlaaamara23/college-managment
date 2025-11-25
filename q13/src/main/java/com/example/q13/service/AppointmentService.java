package com.example.q13.service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.q13.dto.AppointmentCreateDto;
import com.example.q13.dto.AppointmentDto;

@Service
public class AppointmentService {
    private List<AppointmentDto> appointments = new ArrayList<>();
    private Long nextId = 1L;

    private final DoctorService doctorService;
    private final PatientService patientService;

    public AppointmentService(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    // CREATE - Create new appointment
    public AppointmentDto createAppointment(AppointmentCreateDto dto) {
        

        AppointmentDto appointment = new AppointmentDto();
        appointment.setId(nextId++);
        appointment.setDoctorId(dto.getDoctorId());
        appointment.setPatientId(dto.getPatientId());
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus(dto.getStatus());
        appointment.setDuration(dto.getDuration());
        appointment.setPriority(dto.getPriority());
        appointment.setNotes(dto.getNotes());
        
        appointments.add(appointment);
        
        
        return appointment;
    }

    // READ - Get all appointments
    public List<AppointmentDto> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    // READ - Get appointment by ID
    public AppointmentDto getAppointmentById(Long id) {
        return appointments.stream()
                .filter(appointment -> appointment.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    
    public AppointmentDto updateAppointment(Long id, AppointmentCreateDto dto) {
        AppointmentDto existingAppointment = getAppointmentById(id);

        

        existingAppointment.setDoctorId(dto.getDoctorId());
        existingAppointment.setPatientId(dto.getPatientId());
        existingAppointment.setAppointmentDate(dto.getAppointmentDate());
        existingAppointment.setAppointmentTime(dto.getAppointmentTime());
        existingAppointment.setStatus(dto.getStatus());
        existingAppointment.setDuration(dto.getDuration());
        existingAppointment.setPriority(dto.getPriority());
        existingAppointment.setNotes(dto.getNotes());
        
        return existingAppointment;
    }

    // DELETE - Delete appointment
    public void deleteAppointment(Long id) {
        AppointmentDto appointment = getAppointmentById(id);
        
        doctorService.removeAppointmentFromDoctor(appointment.getDoctorId(), id);
        
        appointments.remove(appointment);
    }
 // 1. Get appointments by priority
    public List<AppointmentDto> getAppointmentsByPriority(String priority) {
        return appointments.stream()
                .filter(appointment -> priority.equals(appointment.getPriority()))
                .sorted(Comparator.comparing(AppointmentDto::getAppointmentDate)
                        .thenComparing(AppointmentDto::getAppointmentTime))
                .collect(Collectors.toList());
    }

    // 2. Get upcoming appointments
    public List<AppointmentDto> getUpcomingAppointments(int days) {
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(days);
        
        return appointments.stream()
                .filter(appointment -> "SCHEDULED".equals(appointment.getStatus()))
                .filter(appointment -> {
                    LocalDate appointmentDate = LocalDate.parse(appointment.getAppointmentDate());
                    return !appointmentDate.isBefore(today) && !appointmentDate.isAfter(futureDate);
                })
                .sorted(Comparator.comparing(AppointmentDto::getAppointmentDate)
                        .thenComparing(AppointmentDto::getAppointmentTime))
                .collect(Collectors.toList());
    }

    // 3. Get appointment statistics by status
    public Map<String, Long> getAppointmentStatisticsByStatus() {
        return appointments.stream()
                .collect(Collectors.groupingBy(
                    AppointmentDto::getStatus,
                    Collectors.counting()
                ));
    }

    // 4. Get appointments by date range
    public List<AppointmentDto> getAppointmentsByDateRange(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        
        return appointments.stream()
                .filter(appointment -> {
                    LocalDate appointmentDate = LocalDate.parse(appointment.getAppointmentDate());
                    return !appointmentDate.isBefore(start) && !appointmentDate.isAfter(end);
                })
                .sorted(Comparator.comparing(AppointmentDto::getAppointmentDate)
                        .thenComparing(AppointmentDto::getAppointmentTime))
                .collect(Collectors.toList());
    }

    // 5. Get daily schedule for doctor
    public List<AppointmentDto> getDailySchedule(Long doctorId, String date) {
        return appointments.stream()
                .filter(appointment -> doctorId.equals(appointment.getDoctorId()))
                .filter(appointment -> date.equals(appointment.getAppointmentDate()))
                .sorted(Comparator.comparing(AppointmentDto::getAppointmentTime))
                .collect(Collectors.toList());
    }

    // 6. Get available time slots for doctor
    public List<String> getAvailableTimeSlots(Long doctorId, String date) {
        List<String> allSlots = new ArrayList<>();
        
        // Generate all possible time slots (09:00-17:00, every 15 minutes)
        for (int hour = 9; hour < 17; hour++) {
            for (int minute = 0; minute < 60; minute += 15) {
                String time = String.format("%02d:%02d", hour, minute);
                allSlots.add(time);
            }
        }
        
        // Get doctor's appointments for the day
        List<AppointmentDto> doctorAppointments = getDailySchedule(doctorId, date);
        
        // Remove occupied time slots
        for (AppointmentDto appointment : doctorAppointments) {
            LocalTime appointmentTime = LocalTime.parse(appointment.getAppointmentTime());
            int duration = appointment.getDuration();
            
            // Remove the appointment time and overlapping times based on duration
            Iterator<String> iterator = allSlots.iterator();
            while (iterator.hasNext()) {
                String slot = iterator.next();
                LocalTime slotTime = LocalTime.parse(slot);
                
                // Check if slot overlaps with appointment (considering duration)
                if (!slotTime.isBefore(appointmentTime) && 
                    slotTime.isBefore(appointmentTime.plusMinutes(duration))) {
                    iterator.remove();
                }
            }
        }
        
        return allSlots;
    }

    // 7. Get conflicting appointments
    public List<AppointmentDto> getConflictingAppointments(Long doctorId, String date, String time, int duration) {
        LocalTime newAppointmentTime = LocalTime.parse(time);
        LocalTime newAppointmentEndTime = newAppointmentTime.plusMinutes(duration);
        
        return appointments.stream()
                .filter(appointment -> doctorId.equals(appointment.getDoctorId()))
                .filter(appointment -> date.equals(appointment.getAppointmentDate()))
                .filter(appointment -> {
                    LocalTime existingTime = LocalTime.parse(appointment.getAppointmentTime());
                    LocalTime existingEndTime = existingTime.plusMinutes(appointment.getDuration());
                    
                    // Check for time overlap
                    return (newAppointmentTime.isBefore(existingEndTime) && 
                            newAppointmentEndTime.isAfter(existingTime));
                })
                .collect(Collectors.toList());
    }

    // 8. Get most busy day
    public String getMostBusyDay() {
        return appointments.stream()
                .filter(appointment -> "SCHEDULED".equals(appointment.getStatus()))
                .collect(Collectors.groupingBy(
                    AppointmentDto::getAppointmentDate,
                    Collectors.counting()
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No appointments found");
    }

    // 9. Get appointments by duration range
    public List<AppointmentDto> getAppointmentsByDurationRange(int minDuration, int maxDuration) {
        return appointments.stream()
                .filter(appointment -> appointment.getDuration() >= minDuration && 
                                     appointment.getDuration() <= maxDuration)
                .collect(Collectors.toList());
    }

    // 10. Get cancelled appointments report
    public Map<String, Object> getCancelledAppointmentsReport() {
        List<AppointmentDto> cancelledAppointments = appointments.stream()
                .filter(appointment -> "CANCELLED".equals(appointment.getStatus()))
                .collect(Collectors.toList());
        
        Map<Long, Long> cancelledByDoctor = cancelledAppointments.stream()
                .collect(Collectors.groupingBy(
                    AppointmentDto::getDoctorId,
                    Collectors.counting()
                ));
        
        Map<Long, Long> cancelledByPatient = cancelledAppointments.stream()
                .collect(Collectors.groupingBy(
                    AppointmentDto::getPatientId,
                    Collectors.counting()
                ));
        
        Map<String, Object> report = new HashMap<>();
        report.put("totalCancelled", cancelledAppointments.size());
        report.put("cancelledByDoctor", cancelledByDoctor);
        report.put("cancelledByPatient", cancelledByPatient);
        
        return report;
    }
}
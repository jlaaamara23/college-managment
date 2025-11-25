package com.example.q13.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.q13.dto.PatientCreateDto;
import com.example.q13.dto.PatientDto;


@Service
public class PatientService {
private List<PatientDto> patients = new ArrayList<>();
private Long nextId = 1L;

private Integer calculateAge(String dateOfBirth) {
	LocalDate birthDate = LocalDate.parse(dateOfBirth);
    LocalDate today = LocalDate.now();
    return Period.between(birthDate, today).getYears();
}

public PatientDto createPatient(PatientCreateDto dto) {
	 PatientDto newPatient = new PatientDto();
     newPatient.setId(nextId++);
     newPatient.setFirstName(dto.getFirstName());
     newPatient.setLastName(dto.getLastName());
     newPatient.setEmail(dto.getEmail());
     newPatient.setDateOfBirth(dto.getDateOfBirth());
     newPatient.setPhoneNumber(dto.getPhoneNumber());
     newPatient.setAge(calculateAge(dto.getDateOfBirth())); // מחשב גיל אוטומטית

     patients.add(newPatient);
     return newPatient;

}
public List<PatientDto> getAllPatients() {  
	  return patients;
}
public PatientDto getPatientById(Long id) { return patients.stream()
        .filter(p -> p.getId().equals(id))
        .findFirst()
        .orElse(null);}
public PatientDto updatePatient(Long id, PatientCreateDto dto) { 
	Optional<PatientDto> optionalPatient = patients.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();

    if (optionalPatient.isPresent()) {
        PatientDto patient = optionalPatient.get();
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setEmail(dto.getEmail());
        patient.setPhoneNumber(dto.getPhoneNumber());
   
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setAge(calculateAge(dto.getDateOfBirth())); // עדכון גיל מחדש
        return patient;
    }

    return null;
}
public void deletePatient(Long id) {
    patients.removeIf(p -> p.getId().equals(id));
}
//1. Get patients by age range
public List<PatientDto> getPatientsByAgeRange(int minAge, int maxAge) {
    return patients.stream()
            .filter(patient -> patient.getAge() >= minAge && patient.getAge() <= maxAge)
            .sorted(Comparator.comparing(PatientDto::getAge))
            .collect(Collectors.toList());
}

// 2. Get patient age statistics
public Map<String, Object> getPatientAgeStatistics() {
    IntSummaryStatistics stats = patients.stream()
            .mapToInt(PatientDto::getAge)
            .summaryStatistics();

    return Map.of(
        "minAge", stats.getMin(),
        "maxAge", stats.getMax(),
        "averageAge", Math.round(stats.getAverage() * 10.0) / 10.0,
        "totalPatients", stats.getCount()
    );
}

// 3. Get patients grouped by age group
public Map<String, List<PatientDto>> getPatientsGroupedByAgeGroup() {
    return patients.stream()
            .collect(Collectors.groupingBy(patient -> {
                int age = patient.getAge();
                if (age <= 18) return "0-18";
                else if (age <= 35) return "19-35";
                else if (age <= 60) return "36-60";
                else return "61+";
            }));
}

// 4. Get patients with most appointments
public List<PatientDto> getPatientsWithMostAppointments(int limit) {
    return patients.stream()
            .sorted((p1, p2) -> Integer.compare(
                p2.getAppointmentIds().size(), 
                p1.getAppointmentIds().size()
            ))
            .limit(limit)
            .collect(Collectors.toList());
}

// 5. Search patients by name
public List<PatientDto> searchPatientsByName(String keyword) {
    String lowerKeyword = keyword.toLowerCase();
    return patients.stream()
            .filter(patient -> 
                patient.getFirstName().toLowerCase().contains(lowerKeyword) ||
                patient.getLastName().toLowerCase().contains(lowerKeyword)
            )
            .sorted(Comparator.comparing(PatientDto::getLastName))
            .collect(Collectors.toList());
}
}

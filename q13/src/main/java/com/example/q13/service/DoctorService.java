package com.example.q13.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.q13.dto.DoctorCreateDto;
import com.example.q13.dto.DoctorDto;
import com.example.q13.dto.InstructorDto;
import com.example.q13.exception.ResourceNotFoundException;

@Service
public class DoctorService {
private List<DoctorDto> doctors = new ArrayList<>();
private Long nextId = 1L;

public DoctorDto createDoctor(DoctorCreateDto dto) {
	DoctorDto d=new DoctorDto();
	d.setFirstName(dto.getFirstName());
	d.setId(nextId++);
	d.setLastName(dto.getLastName());
	d.setPhoneNumber(dto.getPhoneNumber());
	d.setRating(dto.getRating());
	d.setSpecialization(dto.getSpecialization());
	d.setEmail(dto.getEmail());
	d.setYearsOfExperience(dto.getYearsOfExperience());
	d.setAppointmentIds(new HashSet<>());
	doctors.add(d);
	return d;
}
public List<DoctorDto> getAllDoctors() {
	return new ArrayList<>(doctors);
}
public DoctorDto getDoctorById(Long id) {
	for(DoctorDto d:doctors) {
		if(d.getId().equals(id))
			return d;
	}
	throw new ResourceNotFoundException("instructor not found with id: " + id);

	
}
public DoctorDto updateDoctor(Long id, DoctorCreateDto dto) {
	DoctorDto DoctorToUpdate = null;

    
    for (DoctorDto student : doctors) {
        if (student.getId().equals(id)) {
        	DoctorToUpdate = student;
            break;
        }
    }

    if (DoctorToUpdate == null) {
        throw new ResourceNotFoundException("instructor not found with id: " + id);
    }

    
    for (DoctorDto student : doctors) {
        if (!student.getId().equals(id) && student.getEmail().equalsIgnoreCase(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + dto.getEmail());
        }
    }

    DoctorToUpdate.setFirstName(dto.getFirstName());
    
    DoctorToUpdate.setLastName(dto.getLastName());
    DoctorToUpdate.setPhoneNumber(dto.getPhoneNumber());
    DoctorToUpdate.setRating(dto.getRating());
    DoctorToUpdate.setSpecialization(dto.getSpecialization());
    DoctorToUpdate.setEmail(dto.getEmail());
    DoctorToUpdate.setYearsOfExperience(dto.getYearsOfExperience());
    DoctorToUpdate.setAppointmentIds(new HashSet<>());

    return DoctorToUpdate;
}
public void deleteDoctor(Long id) {
	for(DoctorDto b:doctors) {
		if(b.getId().equals(id))
			doctors.remove(b);
	}
	 throw new ResourceNotFoundException("instructor not found with id: " + id);

	
}
public void removeAppointmentFromDoctor(Long doctorId, Long appointmentId) {
    DoctorDto doctor = getDoctorById(doctorId);
    doctor.getAppointmentIds().remove(appointmentId);
}
public Map<String, List<DoctorDto>> getDoctorsBySpecializationMap(){
	List<DoctorDto> allDoctors = getAllDoctors();    
    return allDoctors.stream()
            .collect(Collectors.groupingBy(
                DoctorDto::getSpecialization,
                Collectors.toList()
            ));
	
}
public List<DoctorDto> getTopRatedDoctors(int limit) {
    return doctors.stream()
            .sorted((d1, d2) -> Double.compare(d2.getRating(), d1.getRating()))
            .limit(limit)
            .collect(Collectors.toList());
}
public List<DoctorDto> getDoctorsByExperienceRange(int minYears, int maxYears) {
    return doctors.stream()
            .filter(doctor -> doctor.getYearsOfExperience() >= minYears && 
                             doctor.getYearsOfExperience() <= maxYears)
            .sorted((d1, d2) -> Integer.compare(d2.getYearsOfExperience(), d1.getYearsOfExperience()))
            .collect(Collectors.toList());
}
public Map<String, Double> getAverageRatingBySpecialization() {
    return doctors.stream()
            .collect(Collectors.groupingBy(
                DoctorDto::getSpecialization,
                Collectors.averagingDouble(DoctorDto::getRating)
            ));
}
public List<DoctorDto> getDoctorsWithMostAppointments(int limit) {
    return doctors.stream()
            .sorted((d1, d2) -> Integer.compare(d2.getAppointmentIds().size(), d1.getAppointmentIds().size()))
            .limit(limit)
            .collect(Collectors.toList());
}
}

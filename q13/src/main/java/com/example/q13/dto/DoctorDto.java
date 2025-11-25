package com.example.q13.dto;

import java.util.Set;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.HashSet;
import java.util.Set;

public class DoctorDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String specialization;
	private String phoneNumber;
	private Integer yearsOfExperience;
	private Double rating;
	private Set<Long> appointmentIds = new HashSet<>();


	public DoctorDto() {}


	public DoctorDto(Long id, String firstName, String lastName, String email,
	String specialization, String phoneNumber,
	Integer yearsOfExperience, Double rating) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.specialization = specialization;
	this.phoneNumber = phoneNumber;
	this.yearsOfExperience = yearsOfExperience;
	this.rating = rating;
	}


	// Getters and setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }


	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }


	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }


	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }


	public String getSpecialization() { return specialization; }
	public void setSpecialization(String specialization) { this.specialization = specialization; }


	public String getPhoneNumber() { return phoneNumber; }
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }


	public Integer getYearsOfExperience() { return yearsOfExperience; }
	public void setYearsOfExperience(Integer yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }


	public Double getRating() { return rating; }
	public void setRating(Double rating) { this.rating = rating; }


	public Set<Long> getAppointmentIds() { return appointmentIds; }
	public void setAppointmentIds(Set<Long> appointmentIds) { this.appointmentIds = appointmentIds; }
	}
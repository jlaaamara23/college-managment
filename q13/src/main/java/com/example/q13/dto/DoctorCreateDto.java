package com.example.q13.dto;

import jakarta.validation.constraints.*;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class DoctorCreateDto {
    
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Specialization is required")
    @Pattern(regexp = "CARDIOLOGY|DERMATOLOGY|NEUROLOGY|PEDIATRICS|ONCOLOGY|ORTHOPEDICS", 
             message = "Specialization must be one of: CARDIOLOGY, DERMATOLOGY, NEUROLOGY, PEDIATRICS, ONCOLOGY, ORTHOPEDICS")
    private String specialization;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^05\\d-\\d{7}$", message = "Phone number must be in format 05X-XXXXXXX")
    private String phoneNumber;

    @NotNull(message = "Years of experience is required")
    @Min(value = 0, message = "Years of experience must be between 0 and 50")
    @Max(value = 50, message = "Years of experience must be between 0 and 50")
    private Integer yearsOfExperience;

    @NotNull(message = "Rating is required")
    @DecimalMin(value = "0.0", message = "Rating must be between 0.0 and 5.0")
    @DecimalMax(value = "5.0", message = "Rating must be between 0.0 and 5.0")
    private Double rating;

    // Constructors
    public DoctorCreateDto() {}

    public DoctorCreateDto(String firstName, String lastName, String email, 
                          String specialization, String phoneNumber, 
                          Integer yearsOfExperience, Double rating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
        this.yearsOfExperience = yearsOfExperience;
        this.rating = rating;
    }

    // Getters and Setters
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

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorCreateDto that = (DoctorCreateDto) o;
        return Objects.equals(firstName, that.firstName) &&
               Objects.equals(lastName, that.lastName) &&
               Objects.equals(email, that.email) &&
               Objects.equals(specialization, that.specialization) &&
               Objects.equals(phoneNumber, that.phoneNumber) &&
               Objects.equals(yearsOfExperience, that.yearsOfExperience) &&
               Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, specialization, phoneNumber, yearsOfExperience, rating);
    }

    @Override
    public String toString() {
        return "DoctorCreateDto{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", email='" + email + '\'' +
               ", specialization='" + specialization + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", yearsOfExperience=" + yearsOfExperience +
               ", rating=" + rating +
               '}';
    }
}
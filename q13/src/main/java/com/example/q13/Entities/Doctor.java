package com.example.q13.Entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer yearsOfExperience;

    @Column(nullable = false)
    private Double rating;

    // Remove appointmentIds primitive collection from the entity; rely on relationship
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments = new HashSet<>();

    // JPA requires a no-arg constructor
    public Doctor() {}

    public Doctor(Long id, String firstName, String lastName, String email,
            String specialization, String phoneNumber, Integer yearsOfExperience, Double rating,
            Set<Appointment> appointments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
        this.yearsOfExperience = yearsOfExperience;
        this.rating = rating;
        this.appointments = appointments == null ? new HashSet<>() : appointments;
    }

    // Getters / setters
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

    public Set<Appointment> getAppointments() { return appointments; }
    public void setAppointments(Set<Appointment> appointments) { this.appointments = appointments; }

    @Override
    public String toString() {
        return "Doctor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", specialization=" + specialization + ", phoneNumber=" + phoneNumber + ", yearsOfExperience="
                + yearsOfExperience + ", rating=" + rating + "]";
    }
}
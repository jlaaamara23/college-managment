package com.example.q13.Entities;

import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    private String dateOfBirth;

    @Transient
    private Integer age;

    @ElementCollection
    @CollectionTable(name = "patient_appointments", joinColumns = @JoinColumn(name = "patient_id"))
    @Column(name = "appointment_id")
    private Set<Long> appointmentIds;

	public Patient(Long id, String firstName, String lastName, String email, String phoneNumber, String dateOfBirth,
			Integer age, Set<Long> appointmentIds) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.appointmentIds = appointmentIds;
	}
    // Default constructor is required for JPA
    public Patient() {}

    // Getters and Setters (as provided originally)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set<Long> getAppointmentIds() {
		return appointmentIds;
	}

	public void setAppointmentIds(Set<Long> appointmentIds) {
		this.appointmentIds = appointmentIds;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + ", age=" + age + ", appointmentIds="
				+ appointmentIds + "]";
	}
    
}
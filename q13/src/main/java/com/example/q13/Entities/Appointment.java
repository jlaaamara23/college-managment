package com.example.q13.Entities;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private LocalDate appointmentDate;

    @Column(nullable = false)
    private LocalTime appointmentTime;

    @Column(nullable = false)
    @Pattern(regexp = "SCHEDULED|COMPLETED|CANCELLED",
             message = "Status must be one of: SCHEDULED, COMPLETED, or CANCELLED")
    private String status;

    @Column(nullable = false)
    private Integer duration; 

    @Column(nullable = false)
    @Pattern(regexp = "LOW|MEDIUM|HIGH|URGENT",
             message = "Priority must be one of: LOW, MEDIUM, HIGH, or URGENT")
    private String priority;

    private String notes;

	public Appointment(Long id, Doctor doctor, Patient patient, LocalDate appointmentDate, LocalTime appointmentTime,
			@Pattern(regexp = "SCHEDULED|COMPLETED|CANCELLED", message = "Status must be one of: SCHEDULED, COMPLETED, or CANCELLED") String status,
			Integer duration,
			@Pattern(regexp = "LOW|MEDIUM|HIGH|URGENT", message = "Priority must be one of: LOW, MEDIUM, HIGH, or URGENT") String priority,
			String notes) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.patient = patient;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.status = status;
		this.duration = duration;
		this.priority = priority;
		this.notes = notes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", doctor=" + doctor + ", patient=" + patient + ", appointmentDate="
				+ appointmentDate + ", appointmentTime=" + appointmentTime + ", status=" + status + ", duration="
				+ duration + ", priority=" + priority + ", notes=" + notes + "]";
	}
    
}

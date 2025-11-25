package com.example.q13.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Pattern;

public class AppointmentDto {
private Long id;
private Long doctorId;
private Long patientId;
private String appointmentDate;
private String appointmentTime;
private String status;
private Integer duration;
private String priority;
private String notes;
// Getters, Setters, Constructors...
public AppointmentDto() {
	
}
public AppointmentDto(Long id, Long doctorId, Long patientId, String appointmentDate, String appointmentTime,
		String status, Integer duration, String priority, String notes) {
	super();
	this.id = id;
	this.doctorId = doctorId;
	this.patientId = patientId;
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
public Long getDoctorId() {
	return doctorId;
}
public void setDoctorId(Long doctorId) {
	this.doctorId = doctorId;
}
public Long getPatientId() {
	return patientId;
}
public void setPatientId(Long patientId) {
	this.patientId = patientId;
}
public String getAppointmentDate() {
	return appointmentDate;
}
public void setAppointmentDate(String appointmentDate) {
	this.appointmentDate = appointmentDate;
}
public String getAppointmentTime() {
	return appointmentTime;
}
public void setAppointmentTime(String appointmentTime) {
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
	return "AppointmentDto [id=" + id + ", doctorId=" + doctorId + ", patientId=" + patientId + ", appointmentDate="
			+ appointmentDate + ", appointmentTime=" + appointmentTime + ", status=" + status + ", duration=" + duration
			+ ", priority=" + priority + ", notes=" + notes + "]";
}

}
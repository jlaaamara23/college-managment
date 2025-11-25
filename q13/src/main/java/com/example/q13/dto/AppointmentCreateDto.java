package com.example.q13.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AppointmentCreateDto {
	 @NotNull(message = "Doctor ID is required")
	    private Long doctorId;

	    @NotNull(message = "Patient ID is required")
	    private Long patientId;

	    @NotBlank(message = "Appointment date is required")
	    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Appointment date must be in format YYYY-MM-DD")
	    private String appointmentDate;

	    @NotBlank(message = "Appointment time is required")
	    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Appointment time must be in format HH:MM")
	    private String appointmentTime;

	    @NotBlank(message = "Status is required")
	    @Pattern(regexp = "SCHEDULED|COMPLETED|CANCELLED", message = "Status must be one of: SCHEDULED, COMPLETED, CANCELLED")
	    private String status;

	    @NotNull(message = "Duration is required")
	    @Min(value = 15, message = "Duration must be one of: 15, 30, 45, 60")
	    @Max(value = 60, message = "Duration must be one of: 15, 30, 45, 60")
	    private Integer duration;

	    @NotBlank(message = "Priority is required")
	    @Pattern(regexp = "LOW|MEDIUM|HIGH|URGENT", message = "Priority must be one of: LOW, MEDIUM, HIGH, URGENT")
	    private String priority;

	    @Size(max = 500, message = "Notes cannot exceed 500 characters")
	    private String notes;

	    // Constructors
	    public AppointmentCreateDto() {}

		public AppointmentCreateDto(@NotNull(message = "Doctor ID is required") Long doctorId,
				@NotNull(message = "Patient ID is required") Long patientId,
				@NotBlank(message = "Appointment date is required") @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Appointment date must be in format YYYY-MM-DD") String appointmentDate,
				@NotBlank(message = "Appointment time is required") @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Appointment time must be in format HH:MM") String appointmentTime,
				@NotBlank(message = "Status is required") @Pattern(regexp = "SCHEDULED|COMPLETED|CANCELLED", message = "Status must be one of: SCHEDULED, COMPLETED, CANCELLED") String status,
				@NotNull(message = "Duration is required") @Min(value = 15, message = "Duration must be one of: 15, 30, 45, 60") @Max(value = 60, message = "Duration must be one of: 15, 30, 45, 60") Integer duration,
				@NotBlank(message = "Priority is required") @Pattern(regexp = "LOW|MEDIUM|HIGH|URGENT", message = "Priority must be one of: LOW, MEDIUM, HIGH, URGENT") String priority,
				@Size(max = 500, message = "Notes cannot exceed 500 characters") String notes) {
			super();
			this.doctorId = doctorId;
			this.patientId = patientId;
			this.appointmentDate = appointmentDate;
			this.appointmentTime = appointmentTime;
			this.status = status;
			this.duration = duration;
			this.priority = priority;
			this.notes = notes;
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
			return "AppointmentCreateDto [doctorId=" + doctorId + ", patientId=" + patientId + ", appointmentDate="
					+ appointmentDate + ", appointmentTime=" + appointmentTime + ", status=" + status + ", duration="
					+ duration + ", priority=" + priority + ", notes=" + notes + "]";
		}
		
	    
	
}

package com.example.q13.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PatientCreateDto {
	@NotBlank
	@Size(min=2,max=50,message="the name is not valid")
	private String firstName;
	@NotBlank
	@Size(min=2,max=50,message="the name is not valid")
	private String lastName;
	 @NotBlank(message = "Email is required")
	@Email(message = "Invalid email format", regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
	private String email;
	 @NotBlank(message = "phoneNumber is required")
	 @Pattern(regexp = "^05\\d-\\d{7}$", message = "Phone number must be in format 05X-XXXXXXX")
	private String phoneNumber;
	 @NotBlank(message = "dateOfBirth is required")
	 @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in format YYYY-MM-DD")
	private String dateOfBirth;
	 public PatientCreateDto() {
		 
	 }
    
	 public PatientCreateDto(@NotBlank @Size(min = 2, max = 50, message = "the name is not valid") String firstName,
			@NotBlank @Size(min = 2, max = 50, message = "the name is not valid") String lastName,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format", regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") String email,
			@NotBlank(message = "phoneNumber is required") @Pattern(regexp = "^05\\d-\\d{7}$", message = "Phone number must be in format 05X-XXXXXXX") String phoneNumber,
			@NotBlank(message = "dateOfBirth is required") @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in format YYYY-MM-DD") String dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
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
	 @Override
	 public String toString() {
		return "PatientCreateDto [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + "]";
	 }
}
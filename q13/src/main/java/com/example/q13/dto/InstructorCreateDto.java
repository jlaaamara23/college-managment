package com.example.q13.dto;
import java.util.Objects;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public class InstructorCreateDto {
	@NotNull
	@NotBlank
	@Size(min=2,max=50,message="the name should be between 2 to 50")
	private String firstName;
	@NotNull
	@NotBlank
	@Size(min=2,max=50,message="the name should be between 2 to 50")
	private String lastName;
	@NotNull
	@NotBlank
	@Email(message = "Invalid email format")
	private String email;
	@NotNull
	@NotBlank
	@Size(min=2,max=100,message="the department should be between 2 to 50")
	private String department;
	
	private String title;

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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "InstructorCreateDto [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", department=" + department + ", title=" + title + "]";
	}

	public InstructorCreateDto(
			@NotNull @NotBlank @Size(min = 2, max = 50, message = "the name should be between 2 to 50") String firstName,
			@NotNull @NotBlank @Size(min = 2, max = 50, message = "the name should be between 2 to 50") String lastName,
			@NotNull @NotBlank @Email(message = "Invalid email format") String email,
			@NotNull @NotBlank @Size(min = 2, max = 100, message = "the department should be between 2 to 50") String department,
			String title) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
		this.title = title;
	}
	
}

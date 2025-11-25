package com.example.q13.dto;

import java.util.List;
import java.util.Objects;

public class InstructorDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String department;
	private String title;
	
	public InstructorDto() {
		
	}

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

	public InstructorDto(Long id, String firstName, String lastName, String email, String department, String title) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
		this.title = title;
	}

	@Override
	public String toString() {
		return "InstructorDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", department=" + department + ", title=" + title + "]";
	}
	
}
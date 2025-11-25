package com.example.q13.dto;

import java.util.Objects;
import java.util.Set;

public class StudentDto {
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String department;
	public int gpa;
	private int year;
	
	
	public StudentDto(){
	}


	public StudentDto(Long id, String firstname, String lastname, String email, String department, int gpa, int year) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.department = department;
		this.gpa = gpa;
		this.year = year;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
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


	public int getGpa() {
		return gpa;
	}


	public void setGpa(int gpa) {
		this.gpa = gpa;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}
	
	
}
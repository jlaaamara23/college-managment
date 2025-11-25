package com.example.q13.dto;

import java.util.Objects;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class StudentCreateDto {
	
		@NotNull
		@NotBlank
		@Size(min=2,max=50,message="the name should be between 2 to 50")
		private String firstname;
		@NotNull
		@NotBlank
		@Size(min=2,max=50,message="the name should be between 2 to 50")
		private String lastname;
		@NotNull
		@NotBlank
		@Email(message = "Invalid email format")
		private String email;
		private String department;
		@Min(value=1900,message="the number too low")
		@Max(value=2025,message="the number too high")
		private int year;
		public int gpa;
		public StudentCreateDto() {
			
		}
		public StudentCreateDto(
				@NotNull @NotBlank @Size(min = 2, max = 50, message = "the name should be between 2 to 50") String firstname,
				@NotNull @NotBlank @Size(min = 2, max = 50, message = "the name should be between 2 to 50") String lastname,
				@NotNull @NotBlank @Email(message = "Invalid email format") String email, String department,
				@Min(value = 1900, message = "the number too low") @Max(value = 2025, message = "the number too high") int year,
				int gpa) {
			super();
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.department = department;
			this.year = year;
			this.gpa = gpa;
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
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public int getGpa() {
			return gpa;
		}
		public void setGpa(int gpa) {
			this.gpa = gpa;
		}
		
		
		
 
}

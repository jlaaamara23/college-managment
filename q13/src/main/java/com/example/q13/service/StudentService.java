package com.example.q13.service;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.q13.dto.StudentCreateDto;
import com.example.q13.dto.StudentDto;
import com.example.q13.exception.ResourceNotFoundException;

import org.springframework.web.server.ResponseStatusException;


@Service
public class StudentService {
	private List<StudentDto> students = new ArrayList<>();
	private Long nextId =1L;
	public StudentDto createStudent(StudentDto dto) {

	    for(StudentDto b: students) {
	        if(b.getEmail().equals(dto.getEmail()))
	            throw new ResourceNotFoundException("Email already exists: " + dto.getEmail());
	    }

	    StudentDto b = new StudentDto();
	    b.setId(nextId++);
	    b.setDepartment(dto.getDepartment());
	    b.setFirstname(dto.getFirstname());
	    b.setEmail(dto.getEmail());
	    b.setGpa(dto.getGpa());
	    b.setLastname(dto.getLastname());
	    b.setYear(dto.getYear());

	    students.add(b);
	    return b;
	}

	public List<StudentDto> getAllStudents(){
		return new ArrayList<>(students);
	}
	public StudentDto getStudentById(Long id) {
		for (StudentDto student : students) {
	        if (student.getId().equals(id)) {  
	            return student;
	        }
	    }
	    throw new ResourceNotFoundException("Student not found with id: " + id);
	}
	public StudentDto updateStudent(Long id, StudentCreateDto dto) {
		StudentDto studentToUpdate = null;

	    
	    for (StudentDto student : students) {
	        if (student.getId().equals(id)) {
	            studentToUpdate = student;
	            break;
	        }
	    }

	    if (studentToUpdate == null) {
	        throw new ResourceNotFoundException("Student not found with id: " + id);
	    }

	    
	    for (StudentDto student : students) {
	        if (!student.getId().equals(id) && student.getEmail().equalsIgnoreCase(dto.getEmail())) {
	            throw new IllegalArgumentException("Email already exists: " + dto.getEmail());
	        }
	    }

	    studentToUpdate.setFirstname(dto.getFirstname());
	    studentToUpdate.setLastname(dto.getLastname());
	    studentToUpdate.setEmail(dto.getEmail());
	    studentToUpdate.setDepartment(dto.getDepartment());
	    studentToUpdate.setYear(dto.getYear());

	    return studentToUpdate;
	}
	public void deleteStudent(Long id) {

	    StudentDto toDelete = null;

	    for (StudentDto b : students) {
	        if (b.getId().equals(id)) {
	            toDelete = b;
	            break;
	        }
	    }

	    if (toDelete == null) {
	        throw new ResourceNotFoundException("Student not found with id: " + id);
	    }

	    students.remove(toDelete);
	}

	public List<StudentDto> getStudentsByMinGpa(Double gpa){
		Double m=0.0;
		List<StudentDto> newlist=new ArrayList<StudentDto>();
		for(StudentDto b:students) {
			if(b.getGpa()<gpa) {
				newlist.add(b);
			}
		}
		return newlist;
	}

}

package com.example.q13.service;
import com.example.q13.dto.InstructorCreateDto;
import com.example.q13.dto.InstructorDto;
import com.example.q13.dto.StudentDto;
import com.example.q13.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
@Service

public class InstructorService {
	private List<InstructorDto> instructors = new ArrayList<>();
	private Long nextId =1L;
	public InstructorDto createInstructor(InstructorCreateDto dto) {
		 InstructorDto instructor = new InstructorDto();
		    instructor.setId(nextId++);
		    instructor.setFirstName(dto.getFirstName());
		    instructor.setLastName(dto.getLastName());
		    instructor.setEmail(dto.getEmail());
		    instructor.setDepartment(dto.getDepartment());
		    instructor.setTitle(dto.getTitle());

		    instructors.add(instructor); 
		    return instructor;
	}
	public List<InstructorDto> getAllInstructors(){
		return new ArrayList<>(instructors);
	}
	public InstructorDto getInstructorById(Long id) {
		for(InstructorDto instructor:instructors) {
			if(instructor.getId().equals(id))
				return instructor;
		}
		throw new ResourceNotFoundException("instructor not found with id: " + id);
	}
	public InstructorDto updateInstructor(Long id, InstructorCreateDto dto) {
		InstructorDto instructorToUpdate = null;

	    
	    for (InstructorDto student : instructors) {
	        if (student.getId().equals(id)) {
	        	instructorToUpdate = student;
	            break;
	        }
	    }

	    if (instructorToUpdate == null) {
	        throw new ResourceNotFoundException("instructor not found with id: " + id);
	    }

	    
	    for (InstructorDto student : instructors) {
	        if (!student.getId().equals(id) && student.getEmail().equalsIgnoreCase(dto.getEmail())) {
	            throw new IllegalArgumentException("Email already exists: " + dto.getEmail());
	        }
	    }

	    instructorToUpdate.setFirstName(dto.getFirstName());
	    instructorToUpdate.setLastName(dto.getLastName());
	    instructorToUpdate.setEmail(dto.getEmail());
	    instructorToUpdate.setDepartment(dto.getDepartment());
	    instructorToUpdate.setTitle(dto.getTitle());

	    return instructorToUpdate;
	}
	public void deleteInstructor(Long id) {
		for(InstructorDto b:instructors) {
			if(b.getId().equals(id))
				instructors.remove(b);
		}
		 throw new ResourceNotFoundException("instructor not found with id: " + id);
	}
	
}

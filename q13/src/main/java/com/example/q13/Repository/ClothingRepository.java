package com.example.q13.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.q13.model.Clothing;


public interface ClothingRepository extends JpaRepository<Clothing, Long>{
	Optional<Clothing> findByTypeAndColorAndSize(String type, String color, String size);

}

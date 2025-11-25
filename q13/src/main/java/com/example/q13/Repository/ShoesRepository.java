package com.example.q13.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.q13.model.Shoes;

public interface ShoesRepository extends JpaRepository<Shoes, Long> {
	Optional<Shoes> findByBrandAndColorAndSize(String brand, String color, int size);

}

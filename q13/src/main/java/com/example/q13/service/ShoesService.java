package com.example.q13.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.q13.Repository.ShoesRepository;
import com.example.q13.model.Shoes;

@Service
public class ShoesService {
	@Autowired
	private final ShoesRepository shoesRepositry;

    public ShoesService(ShoesRepository shoesRepositry) {
        this.shoesRepositry = shoesRepositry;
    }

    public Shoes addshoes(Shoes shoes) {
    	Optional<Shoes> existingShoeOptional = shoesRepositry.findByBrandAndColorAndSize(
    			shoes.getBrand(),
    			shoes.getColor(),
    			shoes.getSize()
            );
    	if (existingShoeOptional.isPresent()) {
           
            Shoes existingShoe = existingShoeOptional.get();
            
            
            int newTotalQuantity = existingShoe.getQuantity() + shoes.getQuantity();
            
           
            existingShoe.setQuantity(newTotalQuantity);
            
            
            return shoesRepositry.save(existingShoe);
        } else {
            
            return shoesRepositry.save(shoes);
        }
    }

    public List<Shoes> getAllShoes() {
        return shoesRepositry.findAll();
    }

    public Map<String, List<Shoes>> getShoesGroupedByBrand() {
        List<Shoes> allShoes = shoesRepositry.findAll();
        
        return allShoes.stream()
            .collect(Collectors.groupingBy(Shoes::getBrand));
    }

}

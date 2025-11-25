package com.example.q13.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.q13.model.Clothing;
import com.example.q13.service.ClothingService;


@RestController
@RequestMapping("/api/clothing")
@CrossOrigin
public class ClothingController {
	@Autowired
	public ClothingService clothingService;
	@GetMapping
	public List<Clothing> getAllClothing(){
		return clothingService.getAllClothing();
	}
	@PostMapping
	public Clothing addClothing(@RequestBody Clothing clothing) {
		return clothingService.addClothing(clothing);
	}
	@GetMapping("/colors")
    public Set<String> getUniqueClothingColors() {
        return clothingService.getUniqueClothingColors();
    }

    
    @GetMapping("/stock-by-type")
    public Map<String, Integer> getClothingStockByType() {
        return clothingService.getClothingStockByType();
    }

    
    @GetMapping("/most-common")
    public List<Clothing> getMostCommonClothing(@RequestParam int limit) {
        return clothingService.getMostCommonClothing(limit);
    }
	

}

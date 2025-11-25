package com.example.q13.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.q13.Repository.ClothingRepository;
import com.example.q13.model.Clothing;
@Service
public class ClothingService {
	@Autowired
	private ClothingRepository clothingRepository;
	public Clothing addClothing(Clothing clothing) {
		Optional<Clothing> existingclothingOptional = clothingRepository.findByTypeAndColorAndSize(
				clothing.getType(),
				clothing.getColor(),
				clothing.getSize()
	        );
		if(existingclothingOptional.isPresent()) {
			Clothing existclothing=existingclothingOptional.get();
			int newquantity=existclothing.getQuantity()+clothing.getQuantity();
			existclothing.setQuantity(newquantity);
			return clothingRepository.save(existclothing);
			}
		else
			return clothingRepository.save(clothing);
		
		}
	public List<Clothing> getAllClothing() {
        return clothingRepository.findAll();
    }
	public Set<String> getUniqueClothingColors(){
		 List<Clothing> allClothing = clothingRepository.findAll();
		 Set<String> uniqueColors = new HashSet<>();
		 for (Clothing s:allClothing) {
			 if(s.getColor()!=null)
				 uniqueColors.add(s.getColor());
		 }
		 return uniqueColors;
	}
	public Map<String, Integer> getClothingStockByType() {
	    List<Clothing> allClothing = clothingRepository.findAll();
	    Map<String, Integer> stockByType = new HashMap<>();

	    for (Clothing s : allClothing) {
	  
	        int currentQty = stockByType.getOrDefault(s.getType(), 0);
	     
	        stockByType.put(s.getType(), currentQty + s.getQuantity());
	    }

	    return stockByType;
	}
public List<Clothing> getMostCommonClothing(int limit){
	 return clothingRepository.findAll().stream()
             .sorted(Comparator.comparingInt(Clothing::getQuantity).reversed()) 
             .limit(limit) 
             .collect(Collectors.toList());
}
}

package com.example.q13.controller;


import com.example.q13.model.*;
import com.example.q13.service.ShoesService;
import com.example.q13.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shoes")
@CrossOrigin
public class ShoesController {

    @Autowired
    private ShoesService shoesService;

    @GetMapping
    public List<Shoes> getAllShoes() {
        return shoesService.getAllShoes();
    }

    
    @PostMapping
    public Shoes addShoes(@RequestBody Shoes shoes) {
        return shoesService.addshoes(shoes);
    }
}

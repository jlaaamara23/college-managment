package com.example.q13.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
	public static class MultiplyRequest {
        private double a;
        private double b;
        public MultiplyRequest() {}
        public MultiplyRequest(double a, double b) {
            this.a = a;
            this.b = b;
        }
        
        public double getA() { return a; }
        public void setA(double a) { this.a = a; }
        public double getB() { return b; }
        public void setB(double b) { this.b = b; }
    }


    @GetMapping("/calc/add")
    public int add(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }
    @GetMapping("calc/substract")
    public int substract(@RequestParam int a, @RequestParam int b) {
        return a - b;
    }
    
    @PostMapping("/calc/multiply")
    public double multiply(@RequestBody MultiplyRequest request) {
        return request.getA() * request.getB();
    }@PostMapping("/calc/divide")
    public double divide(@RequestBody MultiplyRequest request) {
        if (request.getB() == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return request.getA() / request.getB();
    }
    
}

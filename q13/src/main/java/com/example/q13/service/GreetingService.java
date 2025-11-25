package com.example.q13.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    
    public String greet(String name, String language) {
        switch (language.toLowerCase()) {
            case "he":
                return "שלום, " + name;
            case "en":
                return "Hello, " + name;
            case "es":
                return "Hola, " + name;
            default:
                return "Hi, " + name;
        }
    }
}
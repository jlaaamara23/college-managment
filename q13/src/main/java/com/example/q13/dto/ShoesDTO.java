package com.example.q13.dto;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ShoesDTO {

  

    private String brand;

    private int size;

    private String color;

 
    private Integer quantity;

    public ShoesDTO() {
    }

    public ShoesDTO( String brand, int size, String color, Integer quantity) {
  
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}


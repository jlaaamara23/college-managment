package com.example.q13.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ShoesCreateDTO {

    @NotBlank(message = "Brand is required")
    private String brand;

    @Positive(message = "Size must be a positive number")
    private int size;

    @NotBlank(message = "Color is required")
    private String color;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    public ShoesCreateDTO() {
    }

    public ShoesCreateDTO(String brand, int size, String color, Integer quantity) {
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


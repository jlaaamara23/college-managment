package com.example.q13.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClothingCreateDTO {

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Size is required")
    private String size;

    @NotBlank(message = "Color is required")
    private String color;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    public ClothingCreateDTO() {
    }

    public ClothingCreateDTO(String type, String size, String color, Integer quantity) {
        this.type = type;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
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

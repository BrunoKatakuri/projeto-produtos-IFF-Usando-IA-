package com.example.product_api.dto;

import java.util.UUID;

public class ProductResponseDTO {
    private UUID id;
    private String name;
    private Double price;
    private String description;

    public ProductResponseDTO() {}

    public ProductResponseDTO(UUID id, String name, Double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}

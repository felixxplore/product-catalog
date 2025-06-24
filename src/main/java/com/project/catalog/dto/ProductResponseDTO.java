package com.project.catalog.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponseDTO {
    private String id;
    private String name;
    private Double price;
    private String category;
    private Boolean inStock;
    private LocalDateTime createdAt;
}

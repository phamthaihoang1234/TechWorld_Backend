package com.example.TechWorld.dto.model;

import com.example.TechWorld.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrendingProductDto {
    private String name;
    private Double price;
    private int discount;
    private String image;
    private String description;
    private Category category;
    
}

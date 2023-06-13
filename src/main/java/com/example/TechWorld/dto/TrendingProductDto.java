package com.example.TechWorld.dto;

import org.springframework.stereotype.Component;

import com.example.TechWorld.model.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TrendingProductDto {
    private String name;
    private Double price;
    private int discount;
    private String image;
    private String description;
    private Category category;
    
}

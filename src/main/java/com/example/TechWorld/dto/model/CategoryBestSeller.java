package com.example.TechWorld.dto.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBestSeller {


    private String name;
    private int count;
    private Double amount;
}

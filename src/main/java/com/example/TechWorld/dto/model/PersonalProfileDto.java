package com.example.TechWorld.dto.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalProfileDto {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private Boolean gender;
    private String image;
    private LocalDate registerDate;
}

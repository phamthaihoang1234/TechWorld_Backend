package com.example.TechWorld.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {


    private String name;
    private String email;
    private String password;

    private Boolean gender;
    private String phone;

    private Boolean status;
    private String address;

    private LocalDate registerDate;
    private String image;


    private Set<String> role;

}

package com.example.TechWorld.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponese {


    private Long id;
    private String email;
    private String name;

    private String token;
    private String type = "Bearer";

    private Boolean gender;

    private String password;
    private String phone;

    private String image;
    private String address;

    private Boolean status;

    private LocalDate registerDate;

    private List<String> roles;

    public JwtResponese(String accessToken, Long id, String name, String email, String password, String phone,
                       String address, Boolean gender, Boolean status, String image, LocalDate registerDate, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.name = name;
        this.email = email;
        this.registerDate = registerDate;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.status = status;
        this.image = image;
        this.roles = roles;
    }


}

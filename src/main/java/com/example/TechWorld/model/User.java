package com.example.TechWorld.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String email;
    private String password;

    private Boolean status;
    private String phoneNumber;
    private LocalDate registerDate;
    private Boolean gender;

    private String token;
    private String address;
    private String image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String name, String email, String password, String phone, String address, Boolean gender,
                Boolean status, String image, LocalDate registerDate, String token) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phone;
        this.address = address;
        this.gender = gender;
        this.status = status;
        this.image = image;
        this.registerDate = registerDate;
        this.token = token;
    }


}

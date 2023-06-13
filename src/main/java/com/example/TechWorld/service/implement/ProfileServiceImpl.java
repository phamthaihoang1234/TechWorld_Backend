package com.example.TechWorld.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TechWorld.repository.UserRepository;
import com.example.TechWorld.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    UserRepository userRepo;

    
}

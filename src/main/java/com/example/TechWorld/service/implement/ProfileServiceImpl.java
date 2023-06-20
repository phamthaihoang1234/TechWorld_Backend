package com.example.TechWorld.service.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TechWorld.dto.request.CurrentUserRequest;
import com.example.TechWorld.model.User;
import com.example.TechWorld.repository.UserRepository;
import com.example.TechWorld.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    UserRepository userRepo;

    @Override
    public User getCurrentUser(CurrentUserRequest curRequest) {
        return userRepo.findByEmail(curRequest.getUsername()).get();
    }

    
}

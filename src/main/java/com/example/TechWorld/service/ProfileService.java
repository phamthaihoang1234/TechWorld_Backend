package com.example.TechWorld.service;

import org.springframework.stereotype.Service;

import com.example.TechWorld.dto.request.CurrentUserRequest;
import com.example.TechWorld.model.User;

@Service
public interface ProfileService {
    public abstract User getCurrentUser(CurrentUserRequest username);
}

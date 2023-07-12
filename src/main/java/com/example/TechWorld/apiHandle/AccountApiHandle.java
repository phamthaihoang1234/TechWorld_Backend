package com.example.TechWorld.apiHandle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TechWorld.dto.request.CurrentUserRequest;
import com.example.TechWorld.service.implement.ProfileServiceImpl;

@RestController
@RequestMapping("api/account")
public class AccountApiHandle {
    @Autowired
    ProfileServiceImpl profileService;
    
    @PostMapping("/profile")
    public ResponseEntity<?> getPersonalProfile(@RequestBody CurrentUserRequest curRequest) {
        return ResponseEntity.ok(profileService.getCurrentUser(curRequest));
    }
}

package com.example.TechWorld.controller.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TechWorld.common.Mapper;
import com.example.TechWorld.dto.model.PersonalProfileDto;
import com.example.TechWorld.dto.request.CurrentUserRequest;
import com.example.TechWorld.service.implement.ProfileServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/account")
public class ProfileController {
    
    @Autowired
    ProfileServiceImpl profileService;
    
    @GetMapping("profile")
    public ResponseEntity<?> getPersonalProfile(@RequestBody CurrentUserRequest curRequest) {
        return ResponseEntity.ok(Mapper.modelMapper.map(profileService.getCurrentUser(curRequest), PersonalProfileDto.class));
    }

}
package com.example.TechWorld.apiHandle;


import com.example.TechWorld.model.Rate;
import com.example.TechWorld.repository.OrderDetailRepository;
import com.example.TechWorld.repository.ProductRepository;
import com.example.TechWorld.repository.RateRepository;
import com.example.TechWorld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/rates")
public class RateApiHandle {


    @Autowired
    RateRepository rateRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;


    @GetMapping
    public ResponseEntity<List<Rate>> findAll(){return ResponseEntity.ok(rateRepository.findAllByOrderByIdDesc());}



}

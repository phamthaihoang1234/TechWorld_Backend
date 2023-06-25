package com.example.TechWorld.apiHandle;


import com.example.TechWorld.repository.OrderDetailRepository;
import com.example.TechWorld.repository.ProductRepository;
import com.example.TechWorld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rates")
public class RateApiHandle {


//    @Autowired
//    RateRepository rateRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

}

package com.example.TechWorld.apiHandle;


import com.example.TechWorld.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("api/orders")
public class OrderApiHandle {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderDetailRepository orderDetailRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartDetailRepository cartDetailRepo;

    @Autowired
    private ProductRepository productRepo;

    /*@Autowired
    private SendMailUtil sendMailUtil;*/
}

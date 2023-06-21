package com.example.TechWorld.apiHandle;


import com.example.TechWorld.model.Order;
import com.example.TechWorld.repository.*;
import com.example.TechWorld.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/orders")
public class OrderApiHandle {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SendEmail sendMailUtil;

    @GetMapping
    public ResponseEntity<List<Order>> findAllOrder() {
        List<Order> orders = orderRepository.findAllByOrderByOrdersIdDesc();
        return ResponseEntity.ok(orders);
    }


}

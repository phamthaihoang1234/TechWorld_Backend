package com.example.TechWorld.apiHandle;


import com.example.TechWorld.model.Order;
import com.example.TechWorld.model.User;
import com.example.TechWorld.repository.*;
import com.example.TechWorld.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/{email}")
    public ResponseEntity<List<Order>> getAllOrdersByUser(@PathVariable("email") String email) {
        if (!userRepository.existsByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        User user = userRepository.findByEmail(email).get();
        List<Order> orders = orderRepository.findByUserOrderByOrdersIdDesc(user);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> getByOrderId(@PathVariable("id") Long id) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Order order = orderRepository.findById(id).get();
        return ResponseEntity.ok(order);
    }
}

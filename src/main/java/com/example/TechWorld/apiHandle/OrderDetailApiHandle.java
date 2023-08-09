package com.example.TechWorld.apiHandle;


import com.example.TechWorld.model.OrderDetails;
import com.example.TechWorld.repository.OrderDetailRepository;
import com.example.TechWorld.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/orderDetail")
public class OrderDetailApiHandle {

    @Autowired
    OrderDetailRepository orderDetailRepo;

    @Autowired
    OrderRepository orderRepo;

    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderDetails>> getAllOrderDetailsByOrderId (@PathVariable("id") Long id) {
        if (!orderRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDetailRepo.findByOrder(orderRepo.findById(id).get()));
    }

}

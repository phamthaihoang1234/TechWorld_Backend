package com.example.TechWorld.repository;

import com.example.TechWorld.model.Order;
import com.example.TechWorld.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {

    List<OrderDetails> findByOrder(Order order);
}

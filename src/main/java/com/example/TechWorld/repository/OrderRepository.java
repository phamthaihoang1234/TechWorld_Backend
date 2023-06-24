package com.example.TechWorld.repository;


import com.example.TechWorld.model.Order;
import com.example.TechWorld.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUserOrderByOrdersIdDesc(User user);

    List<Order> findByStatus(int status);

    List<Order> findAllByOrderByOrdersIdDesc();
}

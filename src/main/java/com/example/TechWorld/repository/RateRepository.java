package com.example.TechWorld.repository;


import com.example.TechWorld.model.OrderDetails;
import com.example.TechWorld.model.Product;
import com.example.TechWorld.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    List<Rate> findAllByOrderByIdDesc();

    Rate findByOrderDetail(OrderDetails orderDetail);

    List<Rate> findByProductOrderByIdDesc(Product product);





}

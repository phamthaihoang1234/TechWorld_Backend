package com.example.TechWorld.repository;

import com.example.TechWorld.model.Cart;
import com.example.TechWorld.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {


    List<Cart> findByCart(Cart cart);
}

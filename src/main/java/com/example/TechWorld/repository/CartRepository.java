package com.example.TechWorld.repository;

import com.example.TechWorld.model.Cart;
import com.example.TechWorld.model.CartDetail;
import com.example.TechWorld.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUser(User user);
}

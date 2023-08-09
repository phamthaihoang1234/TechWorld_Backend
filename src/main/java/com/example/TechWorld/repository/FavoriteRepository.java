package com.example.TechWorld.repository;

import com.example.TechWorld.model.Favorite;
import com.example.TechWorld.model.Product;
import com.example.TechWorld.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Integer countByProduct(Product product);

    List<Favorite> findByUser(User user);



    Favorite findByProductAndUser(Product product, User user);


}

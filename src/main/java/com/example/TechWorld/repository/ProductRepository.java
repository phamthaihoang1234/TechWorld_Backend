package com.example.TechWorld.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.TechWorld.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "select * from products p\r\n" + //
            "order by entered_date, sold desc\r\n" + //
            "limit 10")
    List<Product> getTrendingProducts();

    @Query(nativeQuery = true, value = "select * from products p order by sold desc limit 10")
    List<Product> getBestSellerProducts();

    @Query(nativeQuery = true, value = "select * from products p order by entered_date desc limit 10")
    List<Product> getHighlightProducts();
}

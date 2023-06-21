package com.example.TechWorld.apiHandle;


import com.example.TechWorld.model.Product;
import com.example.TechWorld.repository.CategoryRepository;
import com.example.TechWorld.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/products")
public class ProductApiHanle {


    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> products = productRepository.findByStatusTrue();
        return ResponseEntity.ok(products);

    }

    @GetMapping("latest")
    public ResponseEntity<List<Product>> getLatestProducts(){
        List<Product> products = productRepository.findByStatusTrueOrderByEnteredDateDesc();
        return ResponseEntity.ok(products);

    }

    @GetMapping("bestseller")
    public ResponseEntity<List<Product>> getBestSellingProducts() {
        List<Product> products = productRepository.findByStatusTrueOrderBySoldDesc();
        return ResponseEntity.ok(products);
    }








}

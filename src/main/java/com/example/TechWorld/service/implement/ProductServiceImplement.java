package com.example.TechWorld.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TechWorld.model.Product;
import com.example.TechWorld.repository.ProductRepository;
import com.example.TechWorld.service.ProductService;

@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Override
    public List<Product> getTrendingProducts() {
        return productRepo.getTrendingProducts();
    }

    @Override
    public List<Product> getBestSelleProducts() {
        return productRepo.getBestSellerProducts();
    }

    @Override
    public List<Product> getHighlightProducts() {
        return productRepo.getHighlightProducts();
    }
    
}

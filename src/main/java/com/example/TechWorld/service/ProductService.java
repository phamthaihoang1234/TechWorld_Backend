package com.example.TechWorld.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import com.example.TechWorld.model.Product;

@Service
public interface ProductService {
    public abstract List<Product> getTrendingProducts();
    public abstract List<Product> getBestSelleProducts();
    public abstract List<Product> getHighlightProducts();
    public abstract List<Product> getAllProducts(int page);
}

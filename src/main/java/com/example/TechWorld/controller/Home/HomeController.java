package com.example.TechWorld.controller.Home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TechWorld.common.Mapper;
import com.example.TechWorld.dto.model.TrendingProductDto;
import com.example.TechWorld.model.Product;
import com.example.TechWorld.service.implement.ProductServiceImplement;

@CrossOrigin("*")
@RestController
@RequestMapping
public class HomeController {
    @Autowired
    private ProductServiceImplement productServiceImpl;

    @GetMapping("trending-products")
    public ResponseEntity<List<TrendingProductDto>> GetTrendingProducts() {
        List<TrendingProductDto> tpdDtos = new ArrayList<>();
        for (Product p : productServiceImpl.getTrendingProducts()) {
            tpdDtos.add(Mapper.modelMapper.map(p, TrendingProductDto.class));
        }
        return ResponseEntity.ok(tpdDtos);
    }

    @GetMapping("bestseller-products")
    public ResponseEntity<List<TrendingProductDto>> GetBestSellerProducts() {
        List<TrendingProductDto> tpdDtos = new ArrayList<>();
        for (Product p : productServiceImpl.getBestSelleProducts()) {
            tpdDtos.add(Mapper.modelMapper.map(p, TrendingProductDto.class));
        }
        return ResponseEntity.ok(tpdDtos);
    }

    @GetMapping("highlight-products")
    public ResponseEntity<List<TrendingProductDto>> GetHighlightProducts() {
        List<TrendingProductDto> tpdDtos = new ArrayList<>();
        for (Product p : productServiceImpl.getHighlightProducts()) {
            tpdDtos.add(Mapper.modelMapper.map(p, TrendingProductDto.class));
        }
        return ResponseEntity.ok(tpdDtos);
    }

    @GetMapping("all-products")
    public ResponseEntity<List<TrendingProductDto>> GetAllProducts() {
        List<TrendingProductDto> tpdDtos = new ArrayList<>();
        for (Product p : productServiceImpl.getAllProducts()) {
            tpdDtos.add(Mapper.modelMapper.map(p, TrendingProductDto.class));
        }
        return ResponseEntity.ok(tpdDtos);
    }
}

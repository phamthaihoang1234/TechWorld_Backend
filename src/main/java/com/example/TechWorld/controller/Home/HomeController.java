package com.example.TechWorld.controller.Home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TechWorld.common.Mapper;
import com.example.TechWorld.dto.model.ProductResponseDto;
import com.example.TechWorld.model.Product;
import com.example.TechWorld.service.implement.ProductServiceImplement;

@CrossOrigin("*")
@RestController
@RequestMapping
public class HomeController {
    @Autowired
    private ProductServiceImplement productServiceImpl;

    @GetMapping("trending-products")
    public ResponseEntity<List<ProductResponseDto>> GetTrendingProducts() {
        List<ProductResponseDto> pDtos = new ArrayList<>();
        for (Product p : productServiceImpl.getTrendingProducts()) {
            pDtos.add(Mapper.modelMapper.map(p, ProductResponseDto.class));
        }
        return ResponseEntity.ok(pDtos);
    }

    @GetMapping("bestseller-products")
    public ResponseEntity<List<ProductResponseDto>> GetBestSellerProducts() {
        List<ProductResponseDto> pDtos = new ArrayList<>();
        for (Product p : productServiceImpl.getBestSelleProducts()) {
            pDtos.add(Mapper.modelMapper.map(p, ProductResponseDto.class));
        }
        return ResponseEntity.ok(pDtos);
    }

    @GetMapping("highlight-products")
    public ResponseEntity<List<ProductResponseDto>> GetHighlightProducts() {
        List<ProductResponseDto> pDtos = new ArrayList<>();
        for (Product p : productServiceImpl.getHighlightProducts()) {
            pDtos.add(Mapper.modelMapper.map(p, ProductResponseDto.class));
        }
        return ResponseEntity.ok(pDtos);
    }

    @GetMapping("all-products")
    public ResponseEntity<List<ProductResponseDto>> GetAllProducts(int page) {
        List<ProductResponseDto> pDtos = new ArrayList<>();
        for (Product p : productServiceImpl.getAllProducts(page)) {
            pDtos.add(Mapper.modelMapper.map(p, ProductResponseDto.class));
        }
        return ResponseEntity.ok(pDtos);
    }
}

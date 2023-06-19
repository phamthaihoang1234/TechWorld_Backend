package com.example.TechWorld.apiHandle;


import com.example.TechWorld.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("api/products")
public class ProductApiHanle {


@Autowired
    private ProductRepository productRepository;




}

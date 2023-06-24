package com.example.TechWorld.apiHandle;


import com.example.TechWorld.model.Category;
import com.example.TechWorld.model.Product;
import com.example.TechWorld.repository.CategoryRepository;
import com.example.TechWorld.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("bestseller-admin")
    public ResponseEntity<List<Product>> getBestSellingProductsForAdmin() {
        List<Product> products = productRepository.findTop10ByOrderBySoldDesc();
        return ResponseEntity.ok(products);
    }

    @GetMapping("suggest/{categoryId}/{productId}")
    public ResponseEntity<List<Product>> suggestProducts(@PathVariable("categoryId") Long categoryId,
                                                         @PathVariable("productId") Long productId) {
        List<Product> products = productRepository.findProductSuggest(categoryId, productId, categoryId, categoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("rated")
    public ResponseEntity<List<Product>> getRatedProducts() {
        List<Product> products = productRepository.findProductRated();
        return ResponseEntity.ok(products);
    }

    @GetMapping("category/{id}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("id") Long id) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Category category = categoryRepository.findById(id).get();
        List<Product> products = productRepository.findByCategory(category);
        return ResponseEntity.ok(products);
    }


    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Product product = productRepository.findById(id).get();
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if (productRepository.existsById(product.getProductId())) {
            return ResponseEntity.badRequest().build();
        }
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> put(@PathVariable("id") Long id, @RequestBody Product product) {
        if (!id.equals(product.getProductId())) {
            return ResponseEntity.badRequest().build();
        }
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productRepository.save(product));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Product p = productRepository.findById(id).get();
        p.setStatus(false);
        productRepository.save(p);
        return ResponseEntity.ok().build();
    }









}

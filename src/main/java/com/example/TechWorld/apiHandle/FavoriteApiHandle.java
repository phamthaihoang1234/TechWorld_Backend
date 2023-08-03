package com.example.TechWorld.apiHandle;


import com.example.TechWorld.model.Favorite;
import com.example.TechWorld.model.Product;
import com.example.TechWorld.model.User;
import com.example.TechWorld.repository.FavoriteRepository;
import com.example.TechWorld.repository.ProductRepository;
import com.example.TechWorld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/favorites")
public class FavoriteApiHandle {


    private final FavoriteRepository favoriteRepo;
    private final UserRepository userRepo;

    private final ProductRepository productRepo;

    @Autowired
    public FavoriteApiHandle(FavoriteRepository favoriteRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.favoriteRepo = favoriteRepository;
        this.userRepo = userRepository;
        this.productRepo = productRepository;
    }

    @GetMapping("email/{email}")
    public ResponseEntity<List<Favorite>> findByEmail(@PathVariable("email") String email) {
        if (userRepo.existsByEmail(email)) {
            return ResponseEntity.ok(favoriteRepo.findByUser(userRepo.findByEmail(email).get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Integer> findByProduct(@PathVariable("id") Long id) {
        if (productRepo.existsById(id)) {
            return ResponseEntity.ok(favoriteRepo.countByProduct(productRepo.getById(id)));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{productId}/{email}")
    public ResponseEntity<Favorite> findByProductAndUser(@PathVariable("productId") Long productId,
                                                         @PathVariable("email") String email) {
        if (userRepo.existsByEmail(email) && productRepo.existsById(productId)) {
            Product product = productRepo.findById(productId).get();
            User user = userRepo.findByEmail(email).get();
            return ResponseEntity.ok(favoriteRepo.findByProductAndUser(product, user));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("email")
    public ResponseEntity<Favorite> post(@RequestBody Favorite favorite) {
        return ResponseEntity.ok(favoriteRepo.save(favorite));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (favoriteRepo.existsById(id)) {
            favoriteRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

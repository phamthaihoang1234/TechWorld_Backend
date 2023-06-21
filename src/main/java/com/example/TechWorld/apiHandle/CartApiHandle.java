package com.example.TechWorld.apiHandle;


import com.example.TechWorld.model.Cart;
import com.example.TechWorld.repository.CartDetailRepository;
import com.example.TechWorld.repository.CartRepository;
import com.example.TechWorld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/cart")
public class CartApiHandle {

    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserRepository userRepository;

    @Autowired
    public CartApiHandle(CartRepository cartRepository, CartDetailRepository cartDetailRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<Cart> getCartUser(@PathVariable("email") String email) {
        if (!userRepository.existsByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartRepository.findByUser(userRepository.findByEmail(email).get()));
    }

    @PutMapping("/user/{email}")
    public ResponseEntity<Cart> putCartUser(@PathVariable("email") String email, @RequestBody Cart cart) {
        if (!userRepository.existsByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartRepository.save(cart));
    }



}

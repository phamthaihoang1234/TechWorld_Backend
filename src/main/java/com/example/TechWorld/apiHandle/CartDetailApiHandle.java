package com.example.TechWorld.apiHandle;


import com.example.TechWorld.model.CartDetail;
import com.example.TechWorld.repository.CartDetailRepository;
import com.example.TechWorld.repository.CartRepository;
import com.example.TechWorld.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/cartDetail")
public class CartDetailApiHandle {

    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartDetailApiHandle(CartDetailRepository cartDetailRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<CartDetail> getOneCartDetail(@PathVariable("id") Long id) {
        if (!cartDetailRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartDetailRepository.findById(id).get());
    }

}

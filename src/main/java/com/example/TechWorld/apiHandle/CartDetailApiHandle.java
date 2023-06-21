package com.example.TechWorld.apiHandle;


import com.example.TechWorld.model.CartDetail;
import com.example.TechWorld.model.Product;
import com.example.TechWorld.repository.CartDetailRepository;
import com.example.TechWorld.repository.CartRepository;
import com.example.TechWorld.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("cart/{id}")
    public ResponseEntity<List<CartDetail>> getAllCartDetailsByCartId(@PathVariable("id") Long id) {
        if (!cartRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartDetailRepository.findByCart(cartRepository.findById(id).get()));
    }

    @PostMapping()
    public ResponseEntity<CartDetail> post(@RequestBody CartDetail detail) {
        if (!cartRepository.existsById(detail.getCart().getCartId())) {
            return ResponseEntity.notFound().build();
        }
        boolean check = false;
        List<Product> productList = productRepository.findByStatusTrue();
        Product product = productRepository.findByProductIdAndStatusTrue(detail.getProduct().getProductId());
        for (Product p : productList) {
            if (p.getProductId() == product.getProductId()) {
                check = true;
                break;
            }
        }
        if (!check) {
            return ResponseEntity.notFound().build();
        }
        List<CartDetail> detailList = cartDetailRepository.findByCart(cartRepository.findById(detail.getCart().getCartId()).get());
        for (CartDetail item : detailList) {
            if (item.getProduct().getProductId() == detail.getProduct().getProductId()) {
                item.setQuantity(item.getQuantity() + 1);
                item.setPrice(item.getPrice() + detail.getPrice());
                return ResponseEntity.ok(cartDetailRepository.save(item));
            }
        }
        return ResponseEntity.ok(cartDetailRepository.save(detail));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!cartDetailRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cartDetailRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<CartDetail> put(@RequestBody CartDetail detail) {
        if (!cartRepository.existsById(detail.getCart().getCartId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartDetailRepository.save(detail));
    }



}

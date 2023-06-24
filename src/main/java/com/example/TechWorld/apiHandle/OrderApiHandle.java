package com.example.TechWorld.apiHandle;


import com.example.TechWorld.model.*;
import com.example.TechWorld.repository.*;
import com.example.TechWorld.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/orders")
public class OrderApiHandle {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SendEmail sendMailUtil;

    @GetMapping
    public ResponseEntity<List<Order>> findAllOrder() {
        List<Order> orders = orderRepository.findAllByOrderByOrdersIdDesc();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<Order>> getAllOrdersByUser(@PathVariable("email") String email) {
        if (!userRepository.existsByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        User user = userRepository.findByEmail(email).get();
        List<Order> orders = orderRepository.findByUserOrderByOrdersIdDesc(user);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> getByOrderId(@PathVariable("id") Long id) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Order order = orderRepository.findById(id).get();
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{email}")
    public ResponseEntity<Order> checkout(@PathVariable("email") String email, @RequestBody Cart cart) {
        if (!userRepository.existsByEmail(email) || !cartRepository.existsById(cart.getCartId())) {
            return ResponseEntity.notFound().build();
        }

        List<CartDetail> items = cartDetailRepository.findByCart(cart);
        Double amount = 0.0;
        for (CartDetail item : items) {
            amount += item.getPrice();
        }

        Order order = orderRepository.save(new Order(0L, new Date(), amount, cart.getAddress(), cart.getPhoneNumber(), 0,
                userRepository.findByEmail(email).get()));

        for (CartDetail item : items) {
            OrderDetails orderDetail = new OrderDetails(0L, item.getQuantity(), item.getPrice(), item.getProduct(), order);
            orderDetailRepository.save(orderDetail);
        }

        for (CartDetail item : items) {
            cartDetailRepository.delete(item);
        }

        sendMailUtil.sendMailOrder(order);
        return ResponseEntity.ok(order);
    }

    @GetMapping("deliver/{orderId}")
    public ResponseEntity<Void> deliverOrderToUser(@PathVariable("orderId") Long id) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Order order = orderRepository.findById(id).get();
        order.setStatus(1);
        orderRepository.save(order);

        sendMailUtil.sendMailOrderDeliver(order);
        return ResponseEntity.ok().build();
    }

    @GetMapping("cancel/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable("orderId") Long id) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Order order = orderRepository.findById(id).get();
        order.setStatus(3);
        orderRepository.save(order);

        sendMailUtil.sendMailOrderCancel(order);
        return ResponseEntity.ok().build();
    }

    private void updateQuantityProduct(Order order) {
        List<OrderDetails> orderDetails = orderDetailRepository.findByOrder(order);
        for (OrderDetails orderDetail : orderDetails) {
            Product product = productRepository.findById(orderDetail.getProduct().getProductId()).get();
            if (product != null) {
                product.setQuantity(product.getQuantity() - orderDetail.getQuantity());
                product.setSold(product.getSold() + orderDetail.getQuantity());
                productRepository.save(product);
            }
        }
    }

    @GetMapping("success/{orderId}")
    public ResponseEntity<Void> successOrder(@PathVariable("orderId") Long id) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Order order = orderRepository.findById(id).get();
        order.setStatus(2);
        orderRepository.save(order);

        sendMailUtil.sendMailOrderSuccess(order);
        updateQuantityProduct(order);
        return ResponseEntity.ok().build();
    }

}

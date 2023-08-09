package com.example.TechWorld.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderDetails")
public class OrderDetails implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;

    private Double price;
    private int quantity;


    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;




    public OrderDetails(Long orderDetailId,  int quantity,Double price, Product product, Order order) {
        this.orderDetailId = orderDetailId;
        this.price = price;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }
}

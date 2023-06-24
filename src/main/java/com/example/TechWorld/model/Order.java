package com.example.TechWorld.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    private String address;
    private String phoneNumber;
    private Date orderDate;
    private Double amount;

    private int status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Order(Long ordersId, Date orderDate, Double amount, String address, String phoneNumber, int status, User user) {
        this.ordersId = ordersId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.amount = amount;
        this.status = status;
        this.user = user;
    }


}

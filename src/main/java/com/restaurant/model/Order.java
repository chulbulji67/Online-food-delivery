package com.restaurant.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "delivery_person_id")
    private DeliveryPerson deliveryPerson;

    private String status;

    private double totalAmount;

    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;


    //Order-orderItem
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    //Order-Payment
    @OneToOne(mappedBy = "order")
    private Payment payment;

    //Order-review
    @OneToOne(mappedBy = "order")
    private Review review;

}

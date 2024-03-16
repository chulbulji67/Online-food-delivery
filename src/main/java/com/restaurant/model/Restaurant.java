package com.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String cuisineType;



    @OneToOne
    @JoinColumn(name = "contact_information_id")
    private ContactInformation contactInformation;

    private String openingHours;
    private LocalTime registrationDate;
    private boolean open;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //Rest-menu

    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menus;

    //Rest-Order
    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;


    //Rest-Address
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews;
}

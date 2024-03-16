package com.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    //Menu-Category
    @OneToMany(mappedBy= "menu")
    private List<Category> categories;

    //Menu-to-Item

    @OneToMany(mappedBy = "menu")
    private List<Item> items;


    // Constructors, getters, and setters
}

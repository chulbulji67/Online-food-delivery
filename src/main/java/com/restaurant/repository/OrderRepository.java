package com.restaurant.repository;

import com.restaurant.model.Address;
import com.restaurant.model.Order;
import com.restaurant.model.Restaurant;
import com.restaurant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    List<Order> findByRestaurant(Restaurant restaurant);
}

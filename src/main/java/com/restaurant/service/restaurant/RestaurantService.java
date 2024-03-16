package com.restaurant.service.restaurant;

import com.restaurant.dto.RestaurantDto;
import com.restaurant.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    public RestaurantDto addRestaurant(Restaurant restaurant);

    public RestaurantDto getRestaurantById(long id);

    public List<RestaurantDto> getAllRestaurant();

    public RestaurantDto updateRestaurantById(long id, Restaurant restaurant);

    public String deleteRestaurantById(long id);
}

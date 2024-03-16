package com.restaurant.service.restaurant;

import com.restaurant.dto.RestaurantDto;
import com.restaurant.exception.restaurantexception.RestaurantNotFoundException;
import com.restaurant.model.Restaurant;
import com.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public RestaurantDto addRestaurant(Restaurant restaurant) {
        return mapRestaurantToRestaurantDto(restaurantRepository.save(restaurant));
    }

    @Override
    public RestaurantDto getRestaurantById(long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()->{throw new RestaurantNotFoundException("Restaurant Not found with id");});
        return mapRestaurantToRestaurantDto(restaurant);
    }

    @Override
    public List<RestaurantDto> getAllRestaurant() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map((this::mapRestaurantToRestaurantDto)).toList();
    }

    @Override
    public RestaurantDto updateRestaurantById(long id, Restaurant restaurant) {
        Restaurant existingRestaurant = restaurantRepository.findById(id).orElseThrow(()->{throw new RestaurantNotFoundException("Restaurant Not found with id");});
        if(restaurant.getName() != null){
            existingRestaurant.setName(restaurant.getName());
        }
        if(restaurant.getDescription() != null){
            existingRestaurant.setDescription(restaurant.getDescription());
        }
        if(restaurant.getContactInformation() != null){
            existingRestaurant.setContactInformation(restaurant.getContactInformation());
        }
        if(restaurant.getCuisineType() != null){
            existingRestaurant.setCuisineType(restaurant.getCuisineType());
        }
        if(restaurant.getAddress() != null){
            existingRestaurant.setAddress(restaurant.getAddress());
        }
        if(restaurant.getMenus() != null){
            existingRestaurant.setMenus(restaurant.getMenus());
        }
        if(restaurant.getOpeningHours() != null){
            existingRestaurant.setOpeningHours(restaurant.getOpeningHours());
        }
        if(restaurant.isOpen() != existingRestaurant.isOpen()){
            existingRestaurant.setOpen(restaurant.isOpen());
        }

        return mapRestaurantToRestaurantDto(restaurantRepository.save(existingRestaurant));
    }

    @Override
    public String deleteRestaurantById(long id) {
        Restaurant existingRestaurant = restaurantRepository.findById(id).orElseThrow(()->{throw new RestaurantNotFoundException("Restaurant Not found with id");});

        return "Restaurant Deleted Successfully";
    }

    private RestaurantDto mapRestaurantToRestaurantDto(Restaurant restaurant) {
        return RestaurantDto.builder()
                .contactInformation(restaurant.getContactInformation())
                .cuisineType(restaurant.getCuisineType())
                .description(restaurant.getDescription())
                .id(restaurant.getId())
                .name(restaurant.getName())
                .open(restaurant.isOpen())
                .openingHours(restaurant.getOpeningHours())
                .registrationDate(restaurant.getRegistrationDate())
                .build();
    }
}

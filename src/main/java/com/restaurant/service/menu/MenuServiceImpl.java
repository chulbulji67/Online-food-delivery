package com.restaurant.service.menu;

import com.restaurant.dto.MenuDto;
import com.restaurant.exception.menuexception.MenuNotFoundException;
import com.restaurant.exception.restaurantexception.RestaurantNotFoundException;
import com.restaurant.model.Menu;
import com.restaurant.model.Restaurant;
import com.restaurant.repository.MenuRepository;
import com.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Override
    public MenuDto addMenuForRestaurant(Menu menu) {
        restaurantRepository.findById(menu.getRestaurant().getId()).orElseThrow(()->{throw new RestaurantNotFoundException("Restaurant not found in which you want to add menu");
        });
        return mapMenuToMenuDto(menuRepository.save(menu));
    }



    @Override
    public MenuDto getMenuById(long id) {
        Menu menu =  menuRepository.findById(id).orElseThrow(()->{throw new MenuNotFoundException("Menu Not fund with the given Id");});
        return mapMenuToMenuDto(menu);
    }

    @Override
    public List<MenuDto> getAllMenu() {
        return menuRepository.findAll().stream().map(this::mapMenuToMenuDto).toList();
    }

    @Override
    public String deleteMenuById(long id) {
        menuRepository.findById(id).orElseThrow(()->{throw new MenuNotFoundException("Menu Not fund with the given Id");});
        menuRepository.deleteById(id);
        return "Menu Deleted Successfully";
    }

    private MenuDto mapMenuToMenuDto(Menu menu) {
        return MenuDto.builder()
                .id(menu.getId())
                .restaurantId(menu.getRestaurant().getId())
                .build();
    }
}

package com.restaurant.service.menu;

import com.restaurant.dto.MenuDto;
import com.restaurant.model.Menu;

import java.util.List;

public interface MenuService {
    public MenuDto addMenuForRestaurant(Menu menu);
    public MenuDto getMenuById(long id);

    public List<MenuDto> getAllMenu();

    public String deleteMenuById(long id);
}

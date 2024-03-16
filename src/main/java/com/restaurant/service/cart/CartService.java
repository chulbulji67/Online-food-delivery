package com.restaurant.service.cart;

import com.restaurant.dto.CartDto;
import com.restaurant.model.Cart;

public interface CartService {
    public CartDto createCartForAUser(Cart cart);

    public CartDto getCartById(long id);

}

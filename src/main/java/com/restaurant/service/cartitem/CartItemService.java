package com.restaurant.service.cartitem;

import com.restaurant.dto.CartItemDto;
import com.restaurant.model.CartItem;

import java.util.List;

public interface CartItemService {

    public CartItemDto addCartItem(CartItem cartItem);

    public List<CartItemDto> getAllCartItem();

    public String deleteCartItemById(long id);


}

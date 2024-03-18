package com.restaurant.service.cart;

import com.restaurant.dto.CartDto;
import com.restaurant.exception.cartnotfoundexception.CartNotFoundException;
import com.restaurant.exception.userexception.UserNotFoundException;
import com.restaurant.model.Cart;
import com.restaurant.model.User;
import com.restaurant.repository.CartRepository;
import com.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public CartDto createCartForAUser(Cart cart) {
        User user = userRepository.findById(cart.getUser().getId()).orElseThrow(()->new UserNotFoundException("User Not found for which You want to and cart"));
        cart.setUser(user);
        return mapCartToCartDto(cartRepository.save(cart));
    }

    @Override
    public CartDto getCartById(long id) {
        return mapCartToCartDto(cartRepository.findById(id).orElseThrow(()->new CartNotFoundException("Cart Not found With given  Id")));
    }

    public CartDto mapCartToCartDto(Cart cart){
        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .build();
    }
}

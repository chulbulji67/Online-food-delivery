package com.restaurant.service.cartitem;

import com.restaurant.dto.CartItemDto;
import com.restaurant.exception.cartitemexception.CartItemNotFoundException;
import com.restaurant.exception.cartnotfoundexception.CartNotFoundException;
import com.restaurant.exception.itemexception.ItemNotFoundException;
import com.restaurant.model.Cart;
import com.restaurant.model.CartItem;
import com.restaurant.model.Item;
import com.restaurant.repository.CartItemRepository;
import com.restaurant.repository.CartRepository;
import com.restaurant.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public CartItemDto addCartItem(CartItem cartItem) {
        //Check if Cart exist
        Cart cart = cartRepository.findById(cartItem.getCart().getId()).orElseThrow(()->new CartNotFoundException("Cart Not found in which you want to add item"));
        Item item = itemRepository.findById(cartItem.getItem().getId()).orElseThrow(()->new ItemNotFoundException("Item Not found in which you want to add item"));
        cartItem.setCart(cart);
        cartItem.setItem(item);

        return mapCartToCartDto(cartItemRepository.save(cartItem));
    }

    @Override
    public List<CartItemDto> getAllCartItem() {
        return cartItemRepository.findAll().stream().map(this::mapCartToCartDto).toList();
    }

    @Override
    public String deleteCartItemById(long id) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(()->new CartItemNotFoundException("Cart Item not found with the given id"));
        cartItemRepository.deleteById(id);
        return"Cart Item Deleted Successfully";
    }

    public CartItemDto mapCartToCartDto(CartItem cartItem){
        return CartItemDto.builder()
                .id(cartItem.getId())
                .itemName(cartItem.getItem().getName())
                .quantity(cartItem.getQuantity())
                .cartId(cartItem.getCart().getId())
                .itemId(cartItem.getItem().getId())
                .build();
    }

}

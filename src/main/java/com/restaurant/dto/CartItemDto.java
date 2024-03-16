package com.restaurant.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartItemDto {
    private Long id;
    private Long cartId;
    private Long itemId;
    private String itemName;
    private int quantity;
}

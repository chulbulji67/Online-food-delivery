package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto {
    private Long id;
    private Long orderId;
    private Long itemId;
    private int quantity;
    private double totalPrice;
}

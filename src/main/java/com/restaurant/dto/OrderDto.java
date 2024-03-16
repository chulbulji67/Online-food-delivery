package com.restaurant.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDto {
    private Long id;
//    private Long userId;
    private String userName;
//    private Long restaurantId;
    private String restaurantName;
//    private Long deliveryPersonId;
    private String deliveryPersonName;
    private String status;
    private double totalAmount;
    private Date orderDate;
    private Long deliveryAddressId;
//    private Long reviewId;
    private String reviewText;
}

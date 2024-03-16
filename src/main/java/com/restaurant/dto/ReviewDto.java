package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDto {
    private Long id;
    private Long userId;
    private Long restaurantId;
    private Long orderId;
    private int rating;
    private String reviewText;
    private Date reviewDate;
}

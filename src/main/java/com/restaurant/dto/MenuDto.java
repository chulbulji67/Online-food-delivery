package com.restaurant.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MenuDto {
    private Long id;
    private Long restaurantId;
}

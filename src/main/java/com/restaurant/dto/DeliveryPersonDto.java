package com.restaurant.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeliveryPersonDto {
    private Long id;
    private Long userId;
    private String userName;
    private boolean availability;
}

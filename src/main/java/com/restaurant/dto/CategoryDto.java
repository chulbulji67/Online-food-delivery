package com.restaurant.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private Long menuId;
}

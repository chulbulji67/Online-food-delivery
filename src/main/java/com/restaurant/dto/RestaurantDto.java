package com.restaurant.dto;

import com.restaurant.model.ContactInformation;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RestaurantDto {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private ContactInformation contactInformation;
    private String openingHours;
    private LocalTime registrationDate;
    private boolean open;
}

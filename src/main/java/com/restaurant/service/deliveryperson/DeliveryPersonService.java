package com.restaurant.service.deliveryperson;

import com.restaurant.dto.DeliveryPersonDto;
import com.restaurant.model.DeliveryPerson;

import java.util.List;
import java.util.Optional;

public interface DeliveryPersonService {
    DeliveryPersonDto addDeliveryPerson(DeliveryPerson deliveryPerson);
    DeliveryPersonDto getDeliveryPersonById(Long id);
    List<DeliveryPersonDto> getAllDeliveryPersons(Boolean availability, String location); // Assuming location filtering is needed
    DeliveryPersonDto updateAvailability(Long id, boolean availability);
    DeliveryPersonDto updateDeliveryPersonDetails(Long id, DeliveryPerson deliveryPersonDetails);
    public String deleteDeliveryPerson(Long id);
}

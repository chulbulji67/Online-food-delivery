package com.restaurant.service.deliveryperson;

import com.restaurant.dto.DeliveryPersonDto;
import com.restaurant.exception.deliverypersonexception.DeliveryPersonNotFoundException;
import com.restaurant.exception.userexception.UserNotFoundException;
import com.restaurant.model.DeliveryPerson;
import com.restaurant.model.User;
import com.restaurant.repository.DeliveryPersonRepository;
import com.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryPersonServiceImpl implements DeliveryPersonService{

    @Autowired
    DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public DeliveryPersonDto addDeliveryPerson(DeliveryPerson deliveryPerson) {
        //if User exists
        User user = userRepository.findById(deliveryPerson.getUser().getId()).orElseThrow(()->new UserNotFoundException("User Not found which you want to save as Delivery Person"));
        deliveryPerson.setUser(user);

        return mapDeliveryPersonToDeliveryPersonDto(deliveryPersonRepository.save(deliveryPerson));
    }

    @Override
    public DeliveryPersonDto getDeliveryPersonById(Long id) {
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(id).orElseThrow(()->new DeliveryPersonNotFoundException("Delivery Person not found with the given Id"));

        return mapDeliveryPersonToDeliveryPersonDto(deliveryPerson);
    }

    @Override
    public List<DeliveryPersonDto> getAllDeliveryPersons(Boolean availability, String location) {
        List<DeliveryPerson> deliveryPeople = deliveryPersonRepository.findAll();
        List<DeliveryPersonDto> filteredDeliveryPeople = deliveryPeople.stream()
                .filter(deliveryPerson -> deliveryPerson.isAvailability() == (availability != null && availability))
                .filter(deliveryPerson -> deliveryPerson.getUser().getAddresses().stream()
                        .anyMatch(address -> location == null || address.getCity().equals(location)))
                .map(this::mapDeliveryPersonToDeliveryPersonDto).toList();
        return filteredDeliveryPeople;
    }

    @Override
    public DeliveryPersonDto updateAvailability(Long id, DeliveryPerson updatedDeliveryPerson) {
        System.out.println("Availability is"+updatedDeliveryPerson.isAvailability());
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(id).orElseThrow(()->new DeliveryPersonNotFoundException("Delivery Person not found with the given Id"));
       deliveryPerson.setAvailability(updatedDeliveryPerson.isAvailability());
       System.out.println("Availability is"+updatedDeliveryPerson.isAvailability());
        return mapDeliveryPersonToDeliveryPersonDto(deliveryPersonRepository.save(deliveryPerson));
    }

    @Override
    public DeliveryPersonDto updateDeliveryPersonDetails(Long id, DeliveryPerson deliveryPersonDetails) {
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(id).orElseThrow(()->new DeliveryPersonNotFoundException("Delivery Person not found with the given Id"));

        return mapDeliveryPersonToDeliveryPersonDto(deliveryPerson);
    }

    @Override
    public String deleteDeliveryPerson(Long id) {
       deliveryPersonRepository.findById(id).orElseThrow(()->new DeliveryPersonNotFoundException("Delivery Person not found with the given Id"));
        deliveryPersonRepository.deleteById(id);

        return "Deleted Successfully";
    }

    public DeliveryPersonDto mapDeliveryPersonToDeliveryPersonDto(DeliveryPerson deliveryPerson){
        return DeliveryPersonDto.builder()
                .availability(deliveryPerson.isAvailability())
                .id(deliveryPerson.getId())
                .userId(deliveryPerson.getUser().getId())
                .userName(deliveryPerson.getUser().getUsername())
                .build();
    }
}

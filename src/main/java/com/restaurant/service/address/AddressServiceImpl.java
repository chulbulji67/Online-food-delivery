package com.restaurant.service.address;

import com.restaurant.dto.AddressDto;
import com.restaurant.exception.addressnotfoundexception.AddressNotFoundExceptin;
import com.restaurant.exception.userexception.UserNotFoundException;
import com.restaurant.model.Address;
import com.restaurant.model.User;
import com.restaurant.repository.AddressRepository;
import com.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    @Override
    public AddressDto addNewAddress(Address address) {
        //Check User exist
        User user = userRepository.findById(address.getUser().getId()).orElseThrow(() -> new UserNotFoundException("User Not found which you want to add Address"));
        address.setUser(user);
        return mapAddressToAddressDto( addressRepository.save(address));
    }

    @Override
    public AddressDto getAddressByAddressId(long id) {
        Address address = addressRepository.findById(id).orElseThrow(()->new AddressNotFoundExceptin("Address not found by id"));

        return mapAddressToAddressDto(address);
    }

    @Override
    public List<AddressDto> getAllAddress() {
        return addressRepository.findAll().stream().map(this::mapAddressToAddressDto).toList();
    }

    @Override
    public AddressDto updateAddressByAddressId(long id, Address address) {
        Address existingAddress = addressRepository.findById(id).orElseThrow(()->new AddressNotFoundExceptin("Address not found by id"));
        if(address.getAddressLine1() != null){
            existingAddress.setAddressLine1(address.getAddressLine1());
        }
        if(address.getAddressLine2() != null){
            existingAddress.setAddressLine2(address.getAddressLine2());
        }
        if(address.getCity() != null){
            existingAddress.setCity(address.getCity());
        }
        if(address.getCountry() != null){
            existingAddress.setCountry(address.getCountry());
        }
        if(address.getZipCode() != null){
            existingAddress.setZipCode(address.getZipCode());
        }
        if(address.getState() != null){
            existingAddress.setState(address.getState());
        }
        return mapAddressToAddressDto(addressRepository.save(existingAddress));
    }

    @Override
    public String deleteAddressByAddressId(long id) {
        addressRepository.findById(id).orElseThrow(()->new AddressNotFoundExceptin("Address not found by id"));
        addressRepository.deleteById(id);
        return "Address Deleted Successfully";
    }
    public AddressDto mapAddressToAddressDto(Address address){
        return AddressDto.builder()
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .country(address.getCountry())
                .id(address.getId())
                .build();
    }
}

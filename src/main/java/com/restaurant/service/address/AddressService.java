package com.restaurant.service.address;

import com.restaurant.dto.AddressDto;
import com.restaurant.model.Address;

import java.util.List;

public interface AddressService {
    public AddressDto addNewAddress(Address address);
    public AddressDto getAddressByAddressId(long id);

    public List<AddressDto> getAllAddress();

    public AddressDto updateAddressByAddressId(long id, Address address);

    public String deleteAddressByAddressId(long id);
}

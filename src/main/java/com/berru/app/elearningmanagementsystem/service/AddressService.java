package com.berru.app.elearningmanagementsystem.service;

import com.berru.app.elearningmanagementsystem.entity.Address;

public interface AddressService {

    Address addAddress(Address address);

    Address updateAddress(Address address);

    Address getAddressById(int addressId);

}
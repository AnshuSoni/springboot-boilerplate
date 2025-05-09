package com.example.demo.services;

import com.example.demo.models.Address;
import com.example.demo.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    // Create or update address
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    // Get all addresses
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Get address by ID
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    // Delete address by ID
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    // Update address by ID
    public Optional<Address> updateAddress(Long id, Address updatedAddress) {
        return addressRepository.findById(id).map(existingAddress -> {
            existingAddress.setPinCode(updatedAddress.getPinCode());
            existingAddress.setLandmark(updatedAddress.getLandmark());
            existingAddress.setSecondStreet(updatedAddress.getSecondStreet());
            existingAddress.setStreetName(updatedAddress.getStreetName());
            existingAddress.setName(updatedAddress.getName());
            existingAddress.setPhoneNumber(updatedAddress.getPhoneNumber());
            existingAddress.setAddressType(updatedAddress.getAddressType());
            return addressRepository.save(existingAddress);
        });
    }
}


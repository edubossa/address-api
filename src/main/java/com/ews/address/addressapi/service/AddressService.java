package com.ews.address.addressapi.service;

import com.ews.address.addressapi.controller.AddressController;
import com.ews.address.addressapi.exception.AddressBadRequest;
import com.ews.address.addressapi.exception.AddressNotFoundException;
import com.ews.address.addressapi.model.Address;
import com.ews.address.addressapi.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address findById(Long id) {
        try {
            Optional<Address> optional = addressRepository.findById(id);
            if (!optional.isPresent()) {
                throw new AddressNotFoundException("Endereco ID: " + id + " nao localizado!");
            }
            Address address = optional.get();
            address.add(linkTo(methodOn(AddressController.class).getById(address.getAddressID())).withSelfRel());
            address.add(linkTo(methodOn(AddressController.class).delete(address.getAddressID())).withSelfRel());
            return address;
        } catch (Exception e) {
            throw new AddressBadRequest(e.getMessage());
        }
    }

    public List<Address> findAll() {
        List<Address> addresses = new ArrayList<>();
        try {
            addressRepository.findAll().iterator().forEachRemaining(address -> {
                address.add(linkTo(methodOn(AddressController.class).getById(address.getAddressID())).withSelfRel());
                address.add(linkTo(methodOn(AddressController.class).delete(address.getAddressID())).withSelfRel());
                addresses.add(address);
            });
        } catch (Exception e) {
            throw new AddressBadRequest(e.getMessage());
        }
        return addresses;
    }

    public Address findByZipCode(String zipCode) {
        try {
            return addressRepository.findAddressByZipCode(zipCode);
        } catch (Exception e) {
            throw new AddressBadRequest(e.getMessage());
        }
    }

    public Address create(Address address) {
        try {
            return addressRepository.save(address);
        } catch (Exception e) {
            throw new AddressBadRequest(e.getMessage());
        }
    }

    public Address update(Address addressParam, Long id) {
        try {
            Optional<Address> address = addressRepository.findById(id);
            if (!address.isPresent()) {
                throw new AddressNotFoundException("Endereco ID: " + id + " nao localizado!");
            }
            addressParam.setAddressID(id);
            return addressRepository.save(addressParam);
        } catch (Exception e) {
            throw new AddressBadRequest(e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            addressRepository.deleteById(id);
        } catch (Exception e) {
            throw new AddressBadRequest(e.getMessage());
        }
    }

}

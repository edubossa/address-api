package com.ews.address.addressapi.repository;

import com.ews.address.addressapi.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    Address findAddressByZipCode(String zipCode);

}

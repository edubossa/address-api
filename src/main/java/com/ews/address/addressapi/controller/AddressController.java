package com.ews.address.addressapi.controller;

import com.ews.address.addressapi.exception.AddressBadRequest;
import com.ews.address.addressapi.exception.AddressNotFoundException;
import com.ews.address.addressapi.model.Address;
import com.ews.address.addressapi.model.ErrorDetails;
import com.ews.address.addressapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 *  Verificar testes
 *  https://github.com/eugenp/tutorials/tree/master/spring-data-rest
 */
@RestController
@CrossOrigin
@RequestMapping("/api/addresses")
public class AddressController {


    @Autowired
    private AddressService addressService;


    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(addressService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Address> create(@Valid @RequestBody Address address) {
        return new ResponseEntity<>(addressService.create(address), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Address address, @PathVariable("id") Long id) {
        return ResponseEntity.ok(addressService.update(address, id));
    }

    @DeleteMapping("/{id}/void")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        addressService.delete(id);
        return ResponseEntity.ok().build();
    }


    @ExceptionHandler(AddressNotFoundException.class)
    public final ResponseEntity<Object> handleAddressNotFoundException(AddressNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressBadRequest.class)
    public final ResponseEntity<Object> handleAddressBadRequest(AddressBadRequest ex) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }



}

package com.ews.address.addressapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AddressBadRequest extends RuntimeException {

    public AddressBadRequest(String message) {
        super(message);
    }

}

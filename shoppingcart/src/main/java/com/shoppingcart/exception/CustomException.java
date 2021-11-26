package com.shoppingcart.exception;


import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    public CustomException(String msg) {

        super(msg);

    }


}

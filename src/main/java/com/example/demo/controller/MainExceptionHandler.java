package com.example.demo.controller;

import com.example.demo.exception.CustomerErrorResponce;
import com.example.demo.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MainExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponce> handleNotFoundException(CustomerNotFoundException customerNotFoundException) {
        CustomerErrorResponce customerErrorResponce = new CustomerErrorResponce(
                HttpStatus.NOT_FOUND.value(),
                customerNotFoundException.getMessage(),
                System.currentTimeMillis());

        return new  ResponseEntity<>(customerErrorResponce, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponce> handleGenericException(Exception exception) {
        CustomerErrorResponce customerErrorResponce = new CustomerErrorResponce(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(customerErrorResponce, HttpStatus.BAD_REQUEST);
    }
}

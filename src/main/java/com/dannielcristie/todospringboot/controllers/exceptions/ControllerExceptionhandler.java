package com.dannielcristie.todospringboot.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dannielcristie.todospringboot.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionhandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    private ResponseEntity<Object> handleBadRequest(ObjectNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}

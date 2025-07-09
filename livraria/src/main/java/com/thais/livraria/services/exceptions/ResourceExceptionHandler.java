package com.thais.livraria.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.thais.livraria.resources.exceptions.StandartError;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ResourceExceptionHandler {

@ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity <StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandartError error = new StandartError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Objeto não encontrado", e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(error);


}

}
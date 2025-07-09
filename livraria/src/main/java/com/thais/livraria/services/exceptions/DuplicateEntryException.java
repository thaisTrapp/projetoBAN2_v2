package com.thais.livraria.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // Retorna 400 Bad Request
public class DuplicateEntryException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DuplicateEntryException(String msg) {
        super(msg);
    }
}
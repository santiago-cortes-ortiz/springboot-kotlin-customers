package com.example.apikotlin.controller;

import com.example.apikotlin.exception.PersonException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
class PersonExceptionAdvice {
    @ExceptionHandler(PersonException::class)
    fun handlePersonException(ex: PersonException):
            ResponseEntity<String> {
        return ResponseEntity.badRequest().body(ex.message);
    }
}

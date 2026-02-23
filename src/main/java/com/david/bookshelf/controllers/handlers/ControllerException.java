package com.david.bookshelf.controllers.handlers;

import com.david.bookshelf.dtos.CustomError;
import com.david.bookshelf.dtos.ValidationError;
import com.david.bookshelf.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerException {

    /**
     * Excecao a ser lancada quando um recurso nao for encontrado
     *
     * @param e       execao do tipo ResourceNotFound
     * @param request informacao do request
     * @return ResponseEntity com status status e body de err
     */
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomError err = new CustomError(Instant.now(), status.value(),
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    /**
     * Excecao a ser lancada quando houver algum erro de validacao nos DTOS
     *
     * @param e       execao do tpo MethodArgumentNotValidException
     * @param request informacao do request
     * @return ResponseEntity com status status e body de err
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentValidation(MethodArgumentNotValidException e,
                                                                HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationError err = new ValidationError(Instant.now(), status.value(),
                "Invalid Data", request.getRequestURI());

        e.getBindingResult().getFieldErrors().forEach(
                f -> err.addError(f.getField(), f.getDefaultMessage())
        );

        return ResponseEntity.status(status).body(err);
    }

}

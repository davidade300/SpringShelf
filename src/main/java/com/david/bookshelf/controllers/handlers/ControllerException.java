package com.david.bookshelf.controllers.handlers;

import com.david.bookshelf.dtos.CustomError;
import com.david.bookshelf.dtos.ValidationError;
import com.david.bookshelf.entities.exceptions.BusinessRuleException;
import com.david.bookshelf.entities.exceptions.DomainValidationException;
import com.david.bookshelf.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerException {

    /**
     * Excecao a ser lancada quando um recurso nao for encontrado
     *
     * @param e       execao do tipo ResourceNotFound
     * @param request informacao do request
     * @return ResponseEntity com status status e body de err
     */
    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
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
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
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

    /**
     * Excecao a ser lancada caso haja algum problema em validacao de dominio
     *
     * @param e       - excecao do tipo DomainValidationException
     * @param request - informacao do request
     * @return ResponseEntity com Status status e body de err
     */
    @ExceptionHandler(value = DomainValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomError> domainValidation(DomainValidationException e,
                                                        HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        CustomError err = new CustomError(Instant.now(), status.value(),
                e.getMessage(), request.getRequestURI());


        return ResponseEntity.status(status).body(err);

    }

    /**
     * Excecao a ser lancada caso haja algum problema relacionado a regras do negocio
     *
     * @param e       - excecao do tipo BusinessRuleException
     * @param request - informacao do request
     * @return ResponseEntity com Status status e body de err
     */
    @ExceptionHandler(value = BusinessRuleException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<CustomError> businessRule(BusinessRuleException e,
                                                    HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;

        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }


    /**
     * Excecao a ser lancada caso haja algum problema relacionado a entidade nao encontrada
     *
     * @param e       - excecao do tipo EntityNotFoundException
     * @param request - informacao do request
     * @return ResponseEntity com Status status e body de err
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomError> entityNotFound(EntityNotFoundException e,
                                                      HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

}

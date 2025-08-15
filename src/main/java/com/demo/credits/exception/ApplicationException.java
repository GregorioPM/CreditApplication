package com.demo.credits.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> invalidArgument(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ResponseEntity<ErrorMessage> resultado;

        List<FieldError> err= ex.getBindingResult().getFieldErrors();
        List<String> errorsForFields = new ArrayList<>();
        for (FieldError errorField : err) {
            String message = errorField.getField() + ": " + errorField.getDefaultMessage();
            errorsForFields.add(message);
        }
        ErrorMessage errorMessage = ErrorMessage.builder()
                .errors(errorsForFields)
                .exception(ex.getClass().getSimpleName())
                .url(request.getRequestURI())
                .operation(request.getMethod())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        resultado = new ResponseEntity<>(errorMessage, HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        return resultado;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CreditNotFoundException.class)
    public ResponseEntity<ErrorMessage> creditNotFound(CreditNotFoundException ex, HttpServletRequest request) {
        ResponseEntity<ErrorMessage> resultado;
        ErrorMessage errorMessage = ErrorMessage.builder()
                .error(ex.getMessage())
                .exception(ex.getClass().getSimpleName())
                .url(request.getRequestURI())
                .operation(request.getMethod())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
        resultado = new ResponseEntity<>(errorMessage, HttpStatus.valueOf(HttpStatus.NOT_FOUND.value()));
        return resultado;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> notReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ResponseEntity<ErrorMessage> resultado;
        ErrorMessage errorMessage = ErrorMessage.builder()
                .error(ex.getMessage())
                .exception(ex.getClass().getSimpleName())
                .url(request.getRequestURI())
                .operation(request.getMethod())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        resultado = new ResponseEntity<>(errorMessage, HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        return resultado;
    }

}

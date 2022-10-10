package br.com.mpbruder.teste02.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class HandlerExceptions extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        Object response = ExceptionDetails.builder()
                .title("Invalid params")
                .message("One or many fields invalid")
                .fields(errors.stream().map(FieldError::getField).collect(Collectors.joining(";")))
                .filedsMessage(errors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(";")))
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, status);
    }
}

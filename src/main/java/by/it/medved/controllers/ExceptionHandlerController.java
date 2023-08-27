package by.it.medved.controllers;

import by.it.medved.dto.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserNotFoundException(EntityNotFoundException exception) {
        log.warn("Exception: ", exception.getMessage());
        return ErrorResponse.builder()
                .errorMessages(Collections.singletonList(exception.getMessage()))
                .errorCount(1)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .time(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        List<String> list = fieldErrors.stream()
                .map(FieldError::getField)
                .toList();
        return ErrorResponse.builder()
                .errorMessages(exception.getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList())
                .errorCount(exception.getErrorCount())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .time(LocalDateTime.now())
                .build();
    }
}
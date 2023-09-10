package by.it.medved.controllers;

import by.it.medved.dto.response.ErrorResponse;
import by.it.medved.errors.Error;
import by.it.medved.exceptions.EncryptionException;
import by.it.medved.exceptions.VerificationException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static by.it.medved.util.Patterns.*;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleUserNotFoundException(EntityNotFoundException exception) {
        log.warn(ERROR_LOG_PATTERN, exception.getMessage());
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .httpStatus(BAD_REQUEST)
                .time(now())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        log.warn(ERROR_LOG_PATTERN, exception.getMessage());
        return ErrorResponse.builder()
                .errors(getErrors(exception))
                .errorsCount(exception.getErrorCount())
                .httpStatus(BAD_REQUEST)
                .time(now())
                .build();
    }

    @ExceptionHandler(EncryptionException.class)
    public ErrorResponse handleEncryptionException(EncryptionException exception) {
        log.warn(ERROR_LOG_PATTERN, exception.getMessage());
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .httpStatus(INTERNAL_SERVER_ERROR)
                .time(now())
                .build();
    }

    @ExceptionHandler(VerificationException.class)
    public ErrorResponse handleVerificationException(VerificationException exception) {
        log.warn(ERROR_LOG_PATTERN, exception.getMessage());
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .httpStatus(BAD_REQUEST)
                .time(now())
                .build();
    }

    private List<Error> getErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new Error(fieldError.getDefaultMessage(), fieldError.getField()))
                .toList();
    }
}
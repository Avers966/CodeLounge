package ru.skillbox.diplom.group35.library.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skillbox.diplom.group35.library.core.annotation.EnableExceptionHandler;

import javax.persistence.EntityNotFoundException;

/**
 * BaseExceptionHandler
 *
 * @author Georgii Vinogradov
 */

@RestControllerAdvice(annotations = EnableExceptionHandler.class)
public class BaseExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity handleException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler({UnauthorizedException.class})
    protected ResponseEntity unauthorizedHandler(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
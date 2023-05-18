package ru.skillbox.diplom.group35.library.core.exception;

import feign.FeignException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import ru.skillbox.diplom.group35.library.core.annotation.EnableExceptionHandler;

import javax.persistence.EntityNotFoundException;

/**
 * BaseExceptionHandler
 *
 * @author Georgii Vinogradov
 */

@RestControllerAdvice(annotations = EnableExceptionHandler.class)
public class BaseExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, FeignException.NotFound.class})
    protected ResponseEntity notFoundHandler() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity unauthorizedHandler() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(UploadImageException.class)
    protected ResponseEntity badRequestHandler() {
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity handleMaxUploadException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("request size is too big");
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity propertyReferenceException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
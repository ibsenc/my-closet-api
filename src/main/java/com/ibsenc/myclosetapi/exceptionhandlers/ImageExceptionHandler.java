package com.ibsenc.myclosetapi.exceptionhandlers;

import com.ibsenc.myclosetapi.data.InvalidFileTypeResponse;
import com.ibsenc.myclosetapi.exceptions.InvalidFileTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class ImageExceptionHandler handles exceptions thrown by image upload endpoints.
 */
@ControllerAdvice
public class ImageExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {InvalidFileTypeException.class})
  protected ResponseEntity<Object> handleInvalidFileType(InvalidFileTypeException e) {
    return new ResponseEntity<>(new InvalidFileTypeResponse(e.getMessage()),
        HttpStatus.BAD_REQUEST);
  }
}


package com.ibsenc.myclosetapi.exceptionhandlers;

import com.ibsenc.myclosetapi.data.InvalidFileTypeResponse;
import com.ibsenc.myclosetapi.data.InvalidInputResponse;
import com.ibsenc.myclosetapi.exceptions.InvalidFileTypeException;
import com.ibsenc.myclosetapi.exceptions.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ImageExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {InvalidInputException.class})
  protected ResponseEntity<Object> handleInvalidInput(InvalidInputException e) {
    return new ResponseEntity<>(new InvalidInputResponse(e.getMessage()),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {InvalidFileTypeException.class})
  protected ResponseEntity<Object> handleInvalidFileType(InvalidFileTypeException e) {
    return new ResponseEntity<>(new InvalidFileTypeResponse(e.getMessage()),
        HttpStatus.BAD_REQUEST);
  }
}


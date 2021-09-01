package com.ibsenc.myclosetapi;

import com.ibsenc.myclosetapi.data.ResourceNotFoundExceptionResponse;
import com.ibsenc.myclosetapi.data.ValidationExceptionResponse;
import com.ibsenc.myclosetapi.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class RestResponseEntityExceptionHandler is a global exception handler class. Includes
 *
 * @ExceptionHandler methods which handle each type of exception encountered.
 * <p>
 * Reference: https://zetcode.com/springboot/controlleradvice/
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Handles ConstraintViolationExceptions which can be thrown when creating or updating a
   * resource.
   *
   * @returns a response containing a list of all violations.
   */
  @ExceptionHandler(value = {ConstraintViolationException.class})
  protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e) {

    List<String> errorMessages = e.getConstraintViolations().stream()
        .map((violation) -> violation.getMessage()).collect(
            Collectors.toList());

    ValidationExceptionResponse response = new ValidationExceptionResponse();
    response.setErrors(errorMessages);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles ResourceNotFoundExceptions, including those for resource Article.
   */
  @ExceptionHandler(value = {ResourceNotFoundException.class})
  protected ResponseEntity<Object> handleNotFound(ResourceNotFoundException e) {
    return new ResponseEntity<>(new ResourceNotFoundExceptionResponse(e.getMessage()),
        HttpStatus.BAD_REQUEST);
  }
}
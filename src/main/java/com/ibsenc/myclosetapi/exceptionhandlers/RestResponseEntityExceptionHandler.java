package com.ibsenc.myclosetapi.exceptionhandlers;

import com.ibsenc.myclosetapi.data.InvalidInputResponse;
import com.ibsenc.myclosetapi.data.ResourceNotFoundResponse;
import com.ibsenc.myclosetapi.data.ValidationViolationResponse;
import com.ibsenc.myclosetapi.exceptions.InvalidInputException;
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
   * Handles InvalidInputExceptions which can be thrown when there is invalid content in the payload.
   */
  @ExceptionHandler(value = {InvalidInputException.class})
  protected ResponseEntity<Object> handleInvalidInput(InvalidInputException e) {
    return new ResponseEntity<>(new InvalidInputResponse(e.getMessage()),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles ConstraintViolationExceptions which can be thrown when creating or updating a
   * resource.
   *
   * @returns a response containing a list of all violations.
   */
  @ExceptionHandler(value = {ConstraintViolationException.class})
  protected ResponseEntity<ValidationViolationResponse> handleConstraintViolation(
      ConstraintViolationException e) {

    List<String> errorMessages = e.getConstraintViolations().stream()
        .map((violation) -> violation.getMessage()).collect(
            Collectors.toList());

    ValidationViolationResponse response = new ValidationViolationResponse();
    response.setErrors(errorMessages);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles ResourceNotFoundExceptions, including those for resource Article and Image.
   */
  @ExceptionHandler(value = {ResourceNotFoundException.class})
  protected ResponseEntity<ResourceNotFoundResponse> handleNotFound(ResourceNotFoundException e) {
    return new ResponseEntity<>(new ResourceNotFoundResponse(e.getMessage()),
        HttpStatus.NOT_FOUND);
  }
}

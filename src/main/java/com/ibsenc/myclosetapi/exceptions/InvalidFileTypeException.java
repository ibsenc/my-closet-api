package com.ibsenc.myclosetapi.exceptions;

public class InvalidFileTypeException extends RuntimeException {

  public InvalidFileTypeException() {
    super("Invalid file type provided. Image file must be of type jpeg or png.");
  }
}

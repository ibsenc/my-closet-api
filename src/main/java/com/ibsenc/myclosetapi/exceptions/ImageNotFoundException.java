package com.ibsenc.myclosetapi.exceptions;

public class ImageNotFoundException extends ResourceNotFoundException {

  public ImageNotFoundException(String fileName) {
    super(fileName, "Image", "file name");
  }
}

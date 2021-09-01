package com.ibsenc.myclosetapi.exceptions;

public abstract class ResourceNotFoundException extends Throwable {

  public ResourceNotFoundException(String id, String resource) {
    super(resource + " not found with ID: " + id);
  }
}

package com.ibsenc.myclosetapi.exceptions;

public abstract class ResourceNotFoundException extends Throwable {

  public ResourceNotFoundException(String identifier, String resource, String identifierType) {
    super(String.format("%s not found with %s: %s.", resource, identifierType, identifier));
  }
}

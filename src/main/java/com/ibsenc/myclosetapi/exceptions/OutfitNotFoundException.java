package com.ibsenc.myclosetapi.exceptions;

public class OutfitNotFoundException extends ResourceNotFoundException {
  public OutfitNotFoundException(String id) {
    super(id, "Outfit", "ID");
  }
}

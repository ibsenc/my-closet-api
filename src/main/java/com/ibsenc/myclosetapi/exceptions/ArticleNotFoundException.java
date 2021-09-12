package com.ibsenc.myclosetapi.exceptions;

public class ArticleNotFoundException extends ResourceNotFoundException {

  public ArticleNotFoundException(String id) {
    super(id, "Article", "ID");
  }
}

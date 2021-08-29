package com.ibsenc.myclosetapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

  @GetMapping("/ping")
  public ResponseEntity<String> index() {
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
    headers.set("Content-Type", "application/json");
    ResponseEntity response = new ResponseEntity("{\"pong\": 123}", headers, HttpStatus.CREATED);
    return response;
  }
}

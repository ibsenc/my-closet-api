package com.ibsenc.myclosetapi.controller;

import com.ibsenc.myclosetapi.model.Article;
import com.ibsenc.myclosetapi.service.ArticleService;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

  private final ArticleService articleService;

  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @GetMapping("/ping")
  public ResponseEntity<String> index() {
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
    headers.set("Content-Type", "application/json");
    ResponseEntity response = new ResponseEntity("{\"pong\": 123}", headers, HttpStatus.CREATED);
    return response;
  }

  @PostMapping("/article")
  public ResponseEntity<Article> createArticle(@RequestBody Article newArticle) {

    final Article article = articleService.createArticle(newArticle);

    return new ResponseEntity<>(article, HttpStatus.CREATED);
  }

  @SneakyThrows
  @GetMapping("/article/{id}")
  public ResponseEntity<Article> getArticle(@PathVariable String id) {

    Article article = articleService.getArticle(id);

    return new ResponseEntity<>(article, HttpStatus.OK);
  }
}

package com.ibsenc.myclosetapi.controller;

import com.ibsenc.myclosetapi.model.Article;
import com.ibsenc.myclosetapi.service.ArticleService;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    final Article article = articleService.getArticle(id);

    return new ResponseEntity<>(article, HttpStatus.OK);
  }

  @DeleteMapping("/article/{id}")
  public ResponseEntity<Article> deleteArticle(@PathVariable String id) {
    articleService.deleteArticle(id);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @SneakyThrows
  @PatchMapping("/article/{id}")
  public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
    final Article updatedArticle = articleService.updateArticle(article);

    return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
  }

  /**
   * Gets all articles using pagination and sorting. Takes in pageNo, pageSize, and sortBy as query
   * parameters with default values. Sorting is ascending by default.
   *
   * Reference: https://howtodoinjava.com/spring-boot2/pagination-sorting-example/
   */
  @GetMapping("/articles")
  public ResponseEntity<List<Article>> getAllArticles(
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "name") String sortBy) {
    List<Article> articleList = articleService.getAllArticles(pageNo, pageSize, sortBy);

    return new ResponseEntity<>(articleList, new HttpHeaders(), HttpStatus.OK);
  }
}

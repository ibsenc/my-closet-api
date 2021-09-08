package com.ibsenc.myclosetapi.controller;

import com.ibsenc.myclosetapi.model.Article;
import com.ibsenc.myclosetapi.repository.ImageRepository;
import com.ibsenc.myclosetapi.service.ArticleService;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/article")
public class ArticleController {

  private final ArticleService articleService;
  @Autowired
  private ImageRepository imageService;

  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @GetMapping("/ping")
  public ResponseEntity<String> pingArticle() {
    // Use these headers to expect response type as json
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
    headers.set("Content-Type", "application/json");

    return new ResponseEntity("{\"pong\": 123}", headers, HttpStatus.CREATED);
  }

  @PostMapping
  public ResponseEntity<Article> createArticle(@RequestBody Article newArticle) {
    return new ResponseEntity<>(articleService.createArticle(newArticle), HttpStatus.CREATED);
  }

  @SneakyThrows
  @GetMapping("/{id}")
  public ResponseEntity<Article> getArticle(@PathVariable String id) {
    return new ResponseEntity<>(articleService.getArticle(id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Article> deleteArticle(@PathVariable String id) {
    articleService.deleteArticle(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @SneakyThrows
  @PatchMapping("/{id}")
  public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
    return new ResponseEntity<>(articleService.updateArticle(article), HttpStatus.OK);
  }

  /**
   * Gets all articles using pagination and sorting. Takes in pageNo, pageSize, and sortBy as query
   * parameters with default values. Sorting is ascending by default.
   *
   * Reference: https://howtodoinjava.com/spring-boot2/pagination-sorting-example/
   */
  @GetMapping
  public ResponseEntity<List<Article>> getAllArticles(
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "name") String sortBy) {
    return new ResponseEntity<>(articleService.getAllArticles(pageNo, pageSize, sortBy),
        new HttpHeaders(), HttpStatus.OK);
  }

  // Image Endpoints

  @PostMapping("/{article_id}/image")
  public ResponseEntity<Article> uploadArticleImage(
      @PathVariable(value = "article_id") String articleId,
      @RequestParam(value = "file") MultipartFile file) {
    return new ResponseEntity<>(articleService.addImageToArticle(articleId, file), HttpStatus.OK);
  }

  @SneakyThrows
  @GetMapping("/image/{fileName}")
  public ResponseEntity<ByteArrayResource> getImage(@PathVariable String fileName) {
    byte[] data = imageService.getImage(fileName);
    ByteArrayResource resource = new ByteArrayResource(data);
    return ResponseEntity
        .ok()
        .contentLength(data.length)
        .header("Content-type", "image/jpeg")
        .body(resource);
  }

  @DeleteMapping("/{article_id}/image/{fileName}")
  public ResponseEntity<String> deleteArticleImage(
      @PathVariable(value = "article_id") String articleId,
      @PathVariable String fileName) {
    articleService.deleteImageFromArticle(articleId, fileName);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/image/{fileName}")
  public ResponseEntity<String> deleteImage(@PathVariable String fileName) {
    imageService.deleteImage(fileName);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}

package com.ibsenc.myclosetapi.controller;

import com.ibsenc.myclosetapi.repository.ImageRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController {

  @Autowired
  private ImageRepository imageRepository;

  @SneakyThrows
  @GetMapping("/{fileName}")
  public ResponseEntity<ByteArrayResource> getImage(@PathVariable String fileName) {
    byte[] data = imageRepository.getImage(fileName);
    ByteArrayResource resource = new ByteArrayResource(data);
    return ResponseEntity
        .ok()
        .contentLength(data.length)
        .header("Content-type", "image/jpeg")
        .body(resource);
  }

  @DeleteMapping("/{fileName}")
  public ResponseEntity<String> deleteImage(@PathVariable String fileName) {
    imageRepository.deleteImage(fileName);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}

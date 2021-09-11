package com.ibsenc.myclosetapi.controller;

import com.ibsenc.myclosetapi.model.Outfit;
import com.ibsenc.myclosetapi.repository.ImageRepository;
import com.ibsenc.myclosetapi.service.OutfitService;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/outfit")
public class OutfitController {

  private final OutfitService outfitService;
//  @Autowired
//  private ImageRepository imageRepository;

  public OutfitController(OutfitService outfitService) {
    this.outfitService = outfitService;
  }

  @GetMapping("/ping")
  public ResponseEntity<String> pingOutfit() {
    // Use these headers to expect response type as json
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
    headers.set("Content-Type", "application/json");

    return new ResponseEntity("{\"pong\": 123}", headers, HttpStatus.CREATED);
  }

  @PostMapping
  public ResponseEntity<Outfit> createOutfit(@RequestBody Outfit newOutfit) {
    return new ResponseEntity<>(outfitService.createOutfit(newOutfit), HttpStatus.CREATED);
  }

  @SneakyThrows
  @GetMapping("/{id}")
  public ResponseEntity<Outfit> getOutfit(@PathVariable String id) {
    return new ResponseEntity<>(outfitService.getOutfit(id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Outfit> deleteOutfit(@PathVariable String id) {
    outfitService.deleteOutfit(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @SneakyThrows
  @PatchMapping("/{id}")
  public ResponseEntity<Outfit> updateOutfit(@RequestBody Outfit outfit) {
    return new ResponseEntity<>(outfitService.updateOutfit(outfit), HttpStatus.OK);
  }

  /**
   * Gets all outfits using pagination and sorting. Takes in pageNo, pageSize, and sortBy as query
   * parameters with default values. Sorting is ascending by default.
   *
   * Reference: https://howtodoinjava.com/spring-boot2/pagination-sorting-example/
   */
  @GetMapping
  public ResponseEntity<List<Outfit>> getAllOutfits(
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "name") String sortBy) {
    return new ResponseEntity<>(outfitService.getAllOutfits(pageNo, pageSize, sortBy),
        new HttpHeaders(), HttpStatus.OK);
  }

  // TODO: AddNewArticleToOutfit

  // TODO: RemoveArticleFromOutfit
}

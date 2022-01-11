package com.ibsenc.myclosetapi.service;

import com.ibsenc.myclosetapi.data.Constants;
import com.ibsenc.myclosetapi.exceptions.ArticleNotFoundException;
import com.ibsenc.myclosetapi.exceptions.InvalidInputException;
import com.ibsenc.myclosetapi.exceptions.ResourceNotFoundException;
import com.ibsenc.myclosetapi.model.Article;
import com.ibsenc.myclosetapi.repository.ArticleRepository;
import com.ibsenc.myclosetapi.repository.ImageRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ArticleService {

  private final ArticleRepository articleRepository;
  private final ImageRepository imageRepository;

  public ArticleService(ArticleRepository articleRepository, ImageRepository imageRepository) {
    this.articleRepository = articleRepository;
    this.imageRepository = imageRepository;
  }

  public Article createArticle(Article newArticle) {
    if (!Constants.ARTICLE_CATEGORIES.contains(newArticle.getCategory().toUpperCase())) {
      throw new InvalidInputException(
          String.format("Found unsupported article category: '%s'", newArticle.getCategory()));
    }

    newArticle.setCategory(newArticle.getCategory().toUpperCase());
    newArticle.setId(UUID.randomUUID().toString());

    // Prevents setting of images in create endpoint
    newArticle.setImageFileNames(new ArrayList<>());

    List<String> upperCaseSeasons =
        newArticle.getSeasons().stream()
            .map(s-> {
              String season = s.toUpperCase();
              return season;
            })
            .collect(Collectors.toList());

    newArticle.setSeasons(upperCaseSeasons);

    return articleRepository.save(newArticle);
  }

  public Article getArticle(String id) throws ArticleNotFoundException {
    return articleRepository.findById(id)
        .orElseThrow(() -> new ArticleNotFoundException(id));
  }

  public void deleteArticle(String id) {
    // Delete all images associated with the article
    try {
      getArticle(id).getImageFileNames().stream()
          .forEach(imageFile -> imageRepository.deleteImage(imageFile));
    } catch (ArticleNotFoundException e) {
      log.error(e.getMessage());
    }
    articleRepository.deleteById(id);
  }

  public Article updateArticle(Article article)
      throws ResourceNotFoundException, IOException {
    final Article existingArticle = getArticle(article.getId());

    if (article.getName() != null) {
      existingArticle.setName(article.getName());
    }

    if (article.getDescription() != null) {
      existingArticle.setDescription(article.getDescription());
    }

    if (article.getImageFileNames() != null) {
      for (String imageFileName : article.getImageFileNames()) {
        imageRepository.getImage(imageFileName);
      }
      existingArticle.setImageFileNames(article.getImageFileNames());
    }

    if (article.getCategory() != null) {
      if (!Constants.ARTICLE_CATEGORIES.contains(article.getCategory().toUpperCase())) {
        throw new InvalidInputException(
            String.format("Found unsupported article category: '%s'", article.getCategory()));
      }

      existingArticle.setCategory(article.getCategory().toUpperCase());
    }

    if (article.getColor() != null) {
      existingArticle.setColor(article.getColor());
    }

    if (article.getBrand() != null) {
      existingArticle.setBrand(article.getBrand());
    }

    if (article.getSeasons() != null) {
      for (String season : article.getSeasons()) {
        if (!Constants.SEASONS.contains(season.toUpperCase())) {
          throw new InvalidInputException(
              String.format("Found unsupported season: '%s'", season.toUpperCase()));
        }
      }

      List<String> upperCaseSeasons =
          article.getSeasons().stream()
              .map(s-> {
                          String season = s.toUpperCase();
                          return season;
                        })
              .collect(Collectors.toList());

      existingArticle.setSeasons(upperCaseSeasons);
    }

    return articleRepository.save(existingArticle);
  }

  @SneakyThrows
  public Article addImageToArticle(String articleId, MultipartFile file) {
    final Article existingArticle = this.getArticle(articleId);

    final String imageFileName = imageRepository.uploadImage(file);

    final List<String> articleImageNames = existingArticle.getImageFileNames();
    articleImageNames.add(imageFileName);
    existingArticle.setImageFileNames(articleImageNames);

    return this.updateArticle(existingArticle);
  }

  public List<Article> getAllArticles(Integer pageNo, Integer pageSize, String sortBy) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    Page<Article> pagedResult = articleRepository.findAll(paging);

    return pagedResult.hasContent() ? pagedResult.getContent() : new ArrayList<>();
  }

  @SneakyThrows
  public void deleteImageFromArticle(String articleId, String fileName) {
    // Remove image from the article's list of imageFileNames
    final Article article = this.getArticle(articleId);

    List<String> filteredFileNames = article.getImageFileNames()
        .stream()
        .filter(f -> !fileName.equals(f))
        .collect(Collectors.toList());

    article.setImageFileNames(filteredFileNames);
    articleRepository.save(article);

    // Delete image from S3
    imageRepository.deleteImage(fileName);
  }
}

package com.ibsenc.myclosetapi.service;

import com.ibsenc.myclosetapi.exceptions.ArticleNotFoundException;
import com.ibsenc.myclosetapi.model.Article;
import com.ibsenc.myclosetapi.repository.ArticleRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

  private final ArticleRepository articleRepository;

  public ArticleService(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public Article createArticle(Article newArticle) {
    newArticle.setId(UUID.randomUUID().toString());
    final Article article = articleRepository.save(newArticle);

    return article;
  }

  public Article getArticle(String id) throws ArticleNotFoundException {
    final Article retrievedArticle = articleRepository.findById(id)
        .orElseThrow(() -> new ArticleNotFoundException(id));

    return retrievedArticle;
  }
}

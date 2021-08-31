package com.ibsenc.myclosetapi.service;

import com.ibsenc.myclosetapi.model.Article;
import com.ibsenc.myclosetapi.repository.ArticleRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

  private ArticleRepository articleRepository;

  public ArticleService(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public Article createArticle(Article newArticle) {
    // This is where you would do your validation and throw and exception if any violations happen

    newArticle.setId(UUID.randomUUID().toString());
    Article article = articleRepository.save(newArticle);

    return article;
  }
}

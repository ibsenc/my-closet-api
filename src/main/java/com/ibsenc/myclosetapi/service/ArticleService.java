package com.ibsenc.myclosetapi.service;

import com.ibsenc.myclosetapi.exceptions.ArticleNotFoundException;
import com.ibsenc.myclosetapi.model.Article;
import com.ibsenc.myclosetapi.repository.ArticleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

  private final ArticleRepository articleRepository;

  public ArticleService(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public Article createArticle(Article newArticle) {
    newArticle.setId(UUID.randomUUID().toString());

    return articleRepository.save(newArticle);
  }

  public Article getArticle(String id) throws ArticleNotFoundException {
    return articleRepository.findById(id)
        .orElseThrow(() -> new ArticleNotFoundException(id));
  }

  public void deleteArticle(String id) {
    articleRepository.deleteById(id);
  }

  public Article updateArticle(Article article) throws ArticleNotFoundException {
    final Article existingArticle = getArticle(article.getId());

    if (article.getName() != null) {
      existingArticle.setName(article.getName());
    }

    if (article.getDescription() != null) {
      existingArticle.setDescription(article.getDescription());
    }

    return articleRepository.save(existingArticle);
  }

  public List<Article> getAllArticles(Integer pageNo, Integer pageSize, String sortBy) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    Page<Article> pagedResult = articleRepository.findAll(paging);

    return pagedResult.hasContent() ? pagedResult.getContent() : new ArrayList<>();
  }
}

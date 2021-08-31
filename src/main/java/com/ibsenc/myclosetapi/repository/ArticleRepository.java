package com.ibsenc.myclosetapi.repository;

import com.ibsenc.myclosetapi.model.Article;
import com.ibsenc.myclosetapi.service.ArticleService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, String> {

}

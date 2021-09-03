package com.ibsenc.myclosetapi.repository;

import com.ibsenc.myclosetapi.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, String> {
}

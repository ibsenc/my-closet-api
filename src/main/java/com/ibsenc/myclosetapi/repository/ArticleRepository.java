package com.ibsenc.myclosetapi.repository;

import com.ibsenc.myclosetapi.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, String> {

}

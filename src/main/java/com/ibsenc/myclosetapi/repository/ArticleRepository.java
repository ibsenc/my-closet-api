package com.ibsenc.myclosetapi.repository;

import com.ibsenc.myclosetapi.model.Article;
import com.ibsenc.myclosetapi.service.ArticleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, String> {

}

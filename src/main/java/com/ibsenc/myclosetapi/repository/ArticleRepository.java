package com.ibsenc.myclosetapi.repository;

import com.ibsenc.myclosetapi.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, String> {

}

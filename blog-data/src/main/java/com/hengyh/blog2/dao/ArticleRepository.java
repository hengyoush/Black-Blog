package com.hengyh.blog2.dao;

import com.hengyh.blog2.Article;
import com.hengyh.blog2.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findByTitleLike(String title, Pageable pageable);
    Page<Article> findByCategory(Category category, Pageable pageable);
}

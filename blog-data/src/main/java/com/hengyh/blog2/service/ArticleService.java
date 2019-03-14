package com.hengyh.blog2.service;

import com.hengyh.blog2.Article;
import com.hengyh.blog2.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {
    /**
     * 根据文章id返回唯一文章
     * @param id
     * @return
     */
    Article showArticleById(Long id);

    /**
     * 根据文章标题搜索文章，分页
     * @param title
     * @param pageable
     * @return
     */
    Page<Article> searchByTitle(String title, Pageable pageable);

    /**
     * 展示所有文章，分页
     * @param pageable
     * @return
     */
    Page<Article> showAllArticles(Pageable pageable);

    Page<Article> showArticlesByCate(Category category, Pageable pageable);
}

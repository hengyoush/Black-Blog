package com.hengyh.blog2.service.impl;

import com.hengyh.blog2.Article;
import com.hengyh.blog2.Category;
import com.hengyh.blog2.dao.ArticleRepository;
import com.hengyh.blog2.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article showArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> {
            throw new ArticleNotFoundException(id);
        });
    }

    @Override
    public Page<Article> searchByTitle(String title, Pageable pageable) {
        return articleRepository.findByTitleLike("%" + title + "%", pageable);
    }

    @Override
    public Page<Article> showAllArticles(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    public Page<Article> showArticlesByCate(Category category, Pageable pageable) {
        return articleRepository.findByCategory(category, pageable);
    }

    private static final class ArticleNotFoundException extends RuntimeException {
        private Long id;

        ArticleNotFoundException(Long id) {
            this.id = id;
        }

        @Override
        public String getMessage() {
            return "article id：" + id + " can't found";
        }
    }
}

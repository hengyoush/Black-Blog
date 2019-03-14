package com.hengyh.blog2.service.impl;

import com.hengyh.blog2.Article;
import com.hengyh.blog2.User;
import com.hengyh.blog2.dao.ArticleRepository;
import com.hengyh.blog2.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService {

    private ArticleRepository articleRepository;

    @Autowired
    public AdminServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article processEdit(Article newArticle) {
        Article article = articleRepository.findById(newArticle.getId()).get();
        article.setContent(newArticle.getContent());
        article.setContentMD(newArticle.getContentMD());
        article.setCategory(newArticle.getCategory());
        article.setTitle(newArticle.getTitle());
        article.setUpdateAt(new Date());

        return articleRepository.save(article);
    }

    @Override
    public Article processAdd(Article newArticle) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        newArticle.setAuthor(user);
        newArticle.setCreateAt(new Date());
        newArticle.setUpdateAt(new Date());

        return articleRepository.save(newArticle);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}

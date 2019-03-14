package com.hengyh.blog2.controller;

import com.hengyh.blog2.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 显示文章
     * @param id 文章id
     * @param model 模型
     * @return 文章详情页面
     */
    @GetMapping
    public String showArticleDetail(@RequestParam("id") Long id, Model model) {
        model.addAttribute("article", articleService.showArticleById(id));
        return "article";
    }
}

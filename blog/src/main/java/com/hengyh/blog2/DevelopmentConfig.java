package com.hengyh.blog2;

import com.hengyh.blog2.dao.ArticleRepository;
import com.hengyh.blog2.dao.UserRepository;
import com.hengyh.blog2.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(ArticleRepository articleRepository, CategoryService categoryService,
                                        UserRepository userRepo, PasswordEncoder encoder) { // user repo for ease of testing with a built-in user
        return args -> {
            Article article = new Article();
            article.setTitle("title1");
            article.setCreateAt(new Date());
            articleRepository.save(article);
            article = new Article();
            article.setTitle("title2");
            article.setCreateAt(new Date());
            article.setContent("html");
            article.setContentMD("markdown");
            articleRepository.save(article);

            for (int i = 0; i < 100 ; i++) {
                article = new Article();
                article.setTitle("titilebat" + i);
                articleRepository.save(article);
            }

            userRepo.save(new User("admin", encoder.encode("admin")));

            Category category = new Category();
            category.setId(1L);
            category.setName("技术");
            categoryService.save(category);
            category = new Category();
            category.setId(2L);
            category.setName("生活");
            categoryService.save(category);
        };
    }

}

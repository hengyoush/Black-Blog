package com.hengyh.blog2;

import com.hengyh.blog2.dao.ArticleRepository;
import com.hengyh.blog2.dao.UserRepository;
import com.hengyh.blog2.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Date;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(ArticleRepository articleRepository, CategoryService categoryService,
                                        UserRepository userRepo, PasswordEncoder encoder) { // user repo for ease of testing with a built-in user
        return args -> {
//            Category tech = new Category();
////            tech.setName("technology");
////            categoryService.save(tech);
////            Category life = new Category();
////            life.setName("life");
////            categoryService.save(life);
////
////            Article article = new Article();
////            article.setTitle("title1");
////            article.setCreateAt(new Date());
////            article.setCategory(Collections.singleton(tech));
////            articleRepository.save(article);
////            article = new Article();
////            article.setTitle("title2");
////            article.setCreateAt(new Date());
////            article.setContent("html");
////            article.setContentMD("markdown");
////            article.setCategory(Collections.singleton(life));
////            articleRepository.save(article);
////
////            for (int i = 0; i < 100 ; i++) {
////                article = new Article();
////                article.setTitle("titilebat" + i);
////                article.setCategory(Collections.singleton(tech));
////                articleRepository.save(article);
////            }

//            userRepo.save(new User("admin", encoder.encode("admin")));
        };
    }

}

package com.hengyh.blog2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 管理端控制器
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String getAdminPage(@RequestParam(value = "type", required = false, defaultValue = "article") String type) {
        return "forward:/admin/" + type;
    }


//    @ResponseBody
//    @GetMapping("/articles")
//    public RestResponseBo getArticles() {
//
//        return RestResponseBo.ok(articleService.showAllArticles(
//                PageRequest.of(0, 12, by(desc("createAt")))));
//    }
}

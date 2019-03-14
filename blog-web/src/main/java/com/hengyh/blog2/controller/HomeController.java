package com.hengyh.blog2.controller;

import com.hengyh.blog2.Category;
import com.hengyh.blog2.service.ArticleService;
import com.hengyh.blog2.service.CategoryService;
import com.hengyh.blog2.util.PageUtils;
import com.hengyh.blog2.util.ViewPropHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;
import static org.springframework.data.domain.Sort.by;

/**
 * 主页控制器
 */
@Controller
public class HomeController {

    // 分页工具类
    private PageUtils pageUtils;
    // 外部配置视图参数Holder
    private ViewPropHolder holder;
    private CategoryService categoryService;
    private ArticleService articleService;

    @Autowired
    public HomeController(ArticleService articleService,
                          PageUtils pageUtils,
                          CategoryService categoryService,
                          ViewPropHolder holder) {
        this.articleService = articleService;
        this.pageUtils = pageUtils;
        this.categoryService = categoryService;
        this.holder = holder;
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getAllCategories();
    }

    /**
     * 根据页数以及关键字以及文章类别显示文章列表
     * 搜索关键字搜索全类别
     * @param pageNum 当前页数
     * @param keyword 关键字
     * @param cateId 文章类别
     * @param model 模型参数
     * @return 主页面
     */
    @GetMapping(path = {"articles"})
    public String
    getArticlesPage(@RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                    @RequestParam(value = "keyword", required = false) String keyword,
                    @RequestParam(value = "cate", defaultValue = "1") Long cateId,
                    Model model) {
        Pageable pageable = PageRequest.of(pageNum,
                holder.getPerPageSize(), by(desc(holder.getArticlesSortBy())));
        if (keyword != null && !keyword.equals("null")) {
            pageUtils.pageHelper(
                    articleService.searchByTitle(keyword, pageable),
                    model);
            model.addAttribute("keyword", keyword);
        } else{
            pageUtils.pageHelper(
                    articleService.showArticlesByCate(
                            categoryService.getCategoryById(cateId), pageable), model);
            model.addAttribute("cate", cateId);
        }

        return "home";
    }

    @GetMapping("/")
    public String showDefaultPage() {
        return "forward:/articles";
    }

    /**
     * 直接forward
     *
     * @param keyword 搜索的关键字
     * @return forward
     */
    @GetMapping(path = "/search")
    public String searchByTitle(@RequestParam("keyword") String keyword) {
        return "forward:/articles";
    }

}

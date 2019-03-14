package com.hengyh.blog2.controller;

import com.hengyh.blog2.Article;
import com.hengyh.blog2.Category;
import com.hengyh.blog2.service.AdminService;
import com.hengyh.blog2.service.ArticleService;
import com.hengyh.blog2.service.CategoryService;
import com.hengyh.blog2.util.PageUtils;
import com.hengyh.blog2.vo.RestResponseBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;
import static org.springframework.data.domain.Sort.by;

@Controller
@RequestMapping(value = "/admin/article")
public class AdminArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private PageUtils pageUtils;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAdminPage(@RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                                @RequestParam(value = "size", defaultValue = "${view.perPageSize}", required = false) Integer size,
                                @RequestParam(value = "sortBy", defaultValue = "createAt", required = false) String sortBy,
                                @RequestParam(value = "keyword", required = false) String keyword,
                                Model model) {
        Pageable pageable = PageRequest.of(pageNum, size, by(desc(sortBy)));
        if (keyword != null && !keyword.equals("null")) {
            pageUtils.pageHelper(
                    articleService.searchByTitle(keyword, pageable),
                    model);
            model.addAttribute("keyword", keyword);
        } else
            pageUtils.pageHelper(articleService.showAllArticles(pageable), model);
        return "/admin/admin-articles";
    }

    /**
     * 直接forward
     *
     * @param keyword 搜索的关键字
     * @return forward
     */
    @GetMapping("/search")
    public String searchByTitle(@RequestParam("keyword") String keyword) {
        return "forward:/admin/article";
    }

    /**
     * 新增文章页面
     * V0.1 增加文章类别
     *
     * @param model 模型
     * @return 编辑页面
     */
    @GetMapping(path = "/new-article")
    public String showAddPage(Model model) {
        Article article = new Article();
        article.setContentMD("");
        model.addAttribute("article", article);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/admin/admin-articleForm";
    }

    /**
     * 编辑文章页面
     * V0.1 增加文章类别
     *
     * @param id 文章id
     * @param model model
     * @return 编辑页面
     */
    @GetMapping(path = "/{id}")
    public String showEditPage(@PathVariable("id") Long id, Model model) {
        Article article = articleService.showArticleById(id);
        model.addAttribute("article", article);
        // 文章已有的类别
        model.addAttribute("activeCategories", article.getCategory());
        // 文章尚未有的类别
        List<Category> categories = categoryService.getAllCategories();
        categories.removeAll(article.getCategory());
        model.addAttribute("categories",categories);

        return "/admin/admin-articleForm";
    }

    /**
     * 提交编辑或新增的页面
     *
     * @param article 文章对象
     * @param content html内容
     * @return 重定向到主页
     */
    @PostMapping
    public String processEdit(@ModelAttribute("article") Article article,
                              @RequestParam("editormd-html-code") String content) {
        article.setContent(content);
        if (article.getId() == null) {
            adminService.processAdd(article);
        } else {
            adminService.processEdit(article);
        }
        return "redirect:/admin";
    }

    /**
     * 根据id删除文章
     * @param id 文章id
     * @return 成功响应
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public RestResponseBo deleteArticle(@PathVariable("id") Long id) {
        adminService.deleteArticle(id);
        return RestResponseBo.ok();
    }
}

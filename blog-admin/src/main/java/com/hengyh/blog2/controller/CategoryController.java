package com.hengyh.blog2.controller;

import com.hengyh.blog2.Category;
import com.hengyh.blog2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/admin-category";
    }

    @GetMapping(path = {"/{id}","/new-category"})
    public String editOrNew(@PathVariable(value = "id",required = false) Long id, Model model) {
        if (id == null)
            model.addAttribute("category", new Category());
        else
            model.addAttribute("category", categoryService.getCategoryById(id));
        return "admin/admin-categoryForm";
    }

    @PostMapping
    public String submit(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/admin/category";
    }

}

package com.hengyh.blog2.service;

import com.hengyh.blog2.Category;

import java.util.List;

public interface CategoryService {
    public static final String DEFAULT_CATE_ID = "2";
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    void save(Category category);
}

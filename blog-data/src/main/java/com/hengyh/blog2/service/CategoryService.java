package com.hengyh.blog2.service;

import com.hengyh.blog2.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    void save(Category category);
}

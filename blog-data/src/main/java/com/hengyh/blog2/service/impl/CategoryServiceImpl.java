package com.hengyh.blog2.service.impl;

import com.hengyh.blog2.Category;
import com.hengyh.blog2.dao.CategoryRepository;
import com.hengyh.blog2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public void save(Category category) {
        repository.save(category);
    }
}

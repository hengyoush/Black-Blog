package com.hengyh.blog2.util;

import com.hengyh.blog2.Category;
import com.hengyh.blog2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryByIdConverter implements Converter<Long, Category> {
    @Autowired
    private CategoryService categoryService;

    @Override
    public Category convert(Long aLong) {
        return categoryService.getCategoryById(aLong);
    }

}

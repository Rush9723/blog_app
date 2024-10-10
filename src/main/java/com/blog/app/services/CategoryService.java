package com.blog.app.services;

import com.blog.app.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    CategoryDto createCategory(CategoryDto category);
    CategoryDto updateCategory(CategoryDto category,Integer categoryId);
    CategoryDto getCategory(Integer categoryId);
    List<CategoryDto> getAllCategories();
    void deleteCategory(Integer categoryId);
}

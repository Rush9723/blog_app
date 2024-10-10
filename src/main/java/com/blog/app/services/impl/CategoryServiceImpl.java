package com.blog.app.services.impl;

import com.blog.app.dto.CategoryDto;
import com.blog.app.entities.Category;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.repositories.CategoryRepository;
import com.blog.app.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto,Category.class);
        Category savedCategory = this.categoryRepository.save(category);
        // fully optimised code
        return this.modelMapper.map(savedCategory,CategoryDto.class);

    }

    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category categoryEntity = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        categoryEntity.setCategoryTitle(categoryDto.getCategoryTitle());
        categoryEntity.setCategoryDescription(categoryDto.getCategoryDescription());
        return this.modelMapper.map(this.categoryRepository.save(categoryEntity), CategoryDto.class);
    }

    public CategoryDto getCategory(Integer categoryId) {
        Category categoryEntity = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        return this.modelMapper.map(categoryEntity, CategoryDto.class);
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = this.categoryRepository.findAll();
        return categoryList.stream().map((categoryEntity) -> this.modelMapper.map(categoryEntity, CategoryDto.class)).collect(Collectors.toList());
    }

    public void deleteCategory(Integer categoryId) {
        Category categoryEntity = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        this.categoryRepository.delete(categoryEntity);
    }
}

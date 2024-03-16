package com.restaurant.service.category;

import com.restaurant.dto.CategoryDto;
import com.restaurant.model.Category;

import java.util.List;

public interface CategoryService {
    public CategoryDto createCategory(Category category);

    public CategoryDto getCategoryById(long id);

    public List<CategoryDto> getAllCategory();

    public CategoryDto updateCategoryById(long id, Category category);

    public String deleteCategoryById(long id);
}

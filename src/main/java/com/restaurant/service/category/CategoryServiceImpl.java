package com.restaurant.service.category;

import com.restaurant.dto.CategoryDto;
import com.restaurant.exception.categoryexception.CategoryNotFoundException;
import com.restaurant.exception.menuexception.MenuNotFoundException;
import com.restaurant.model.Category;
import com.restaurant.model.Menu;
import com.restaurant.repository.CategoryRepository;
import com.restaurant.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MenuRepository menuRepository;

    @Override
    public CategoryDto createCategory(Category category) {
        //Check if Menu exists in which You want to add Category
        Menu menu = menuRepository.findById(category.getMenu().getId()).orElseThrow(()->new MenuNotFoundException("Menu Not Found in which You want to add Category"));
        category.setMenu(menu);
        return mapCategoryToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto getCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category not found with the given id"));
        return mapCategoryToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        return categoryRepository.findAll().stream().map(this::mapCategoryToCategoryDto).toList();
    }

    @Override
    public CategoryDto updateCategoryById(long id, Category category) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category not found with the given id"));
        existingCategory.setName(category.getName());
        return mapCategoryToCategoryDto(categoryRepository.save(existingCategory));
    }

    @Override
    public String deleteCategoryById(long id) {
       categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category not found with the given id"));
        categoryRepository.deleteById(id);
        return "Category Deleted Successfully";
    }

    public CategoryDto mapCategoryToCategoryDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .menuId(category.getMenu().getId())
                .name(category.getName())
                .build();
    }
}

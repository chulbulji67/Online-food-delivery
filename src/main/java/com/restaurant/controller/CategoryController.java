package com.restaurant.controller;

import com.restaurant.model.Category;
import com.restaurant.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        return ResponseEntity.status(201).body(categoryService.createCategory(category));
    }

    @GetMapping
    public ResponseEntity<?> getCategory(){
        return ResponseEntity.status(200).body(categoryService.getAllCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryByid(@PathVariable long id){
        return ResponseEntity.status(200).body(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable long id, @RequestBody Category category){
        return ResponseEntity.status(200).body(categoryService.updateCategoryById(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable long id){
        return ResponseEntity.status(200).body(categoryService.deleteCategoryById(id));
    }

}

package com.springApp.controller;

import com.springApp.model.Category;
import com.springApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\": \"Категория с id " + id + " не найдена.\"}");
        }
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        if (categoryService.existsById(category.getId())) {
            Category updatedCategory = categoryService.save(category);
            return ResponseEntity.ok(updatedCategory);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"message\": \"Категория с id " + category.getId() + " не найдена.\"}");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        if (categoryService.existsById(id)) {
            categoryService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"message\": \"Категория с id " + id + " не найдена.\"}");
    }
}

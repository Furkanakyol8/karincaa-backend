package com.furkan.karincaa.controller;

import com.furkan.karincaa.model.dto.*;
import com.furkan.karincaa.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "category")
public class CategoryController{
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<RootCategoryDto> createRoot(@RequestBody CreateCategoryRequest createCategoryRequest){
        return ResponseEntity.ok(categoryService.createRoot(createCategoryRequest));
    }

    @PostMapping("{parentId}/sub-categories")
    public ResponseEntity<SubCategoryDto> addSubCategory(@PathVariable UUID parentId, @RequestBody CreateCategoryRequest createCategoryRequest){
        return ResponseEntity.ok(categoryService.addSubCategory(parentId, createCategoryRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable UUID id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @GetMapping
    public ResponseEntity<CategoryListResponse> getRootCategories(){
        CategoryListResponse response = new CategoryListResponse(
                categoryService.getAllRootCategories()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

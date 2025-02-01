package com.furkan.karincaa.service;

import com.furkan.karincaa.model.dto.CategoryDto;
import com.furkan.karincaa.model.dto.RootCategoryDto;
import com.furkan.karincaa.model.dto.CreateCategoryRequest;
import com.furkan.karincaa.model.dto.SubCategoryDto;

import java.util.Set;
import java.util.UUID;

public interface CategoryService {
    RootCategoryDto createRoot(CreateCategoryRequest createCategoryRequest);

    SubCategoryDto addSubCategory(UUID parentId, CreateCategoryRequest create);

    CategoryDto findById(UUID id);

    Set<CategoryDto> getAllRootCategories();
}

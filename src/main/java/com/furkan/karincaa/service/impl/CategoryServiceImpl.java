package com.furkan.karincaa.service.impl;

import com.furkan.karincaa.exception.AlreadyExistException;
import com.furkan.karincaa.exception.NotFoundException;
import com.furkan.karincaa.model.dto.CategoryDto;
import com.furkan.karincaa.model.dto.RootCategoryDto;
import com.furkan.karincaa.model.dto.CreateCategoryRequest;
import com.furkan.karincaa.model.dto.SubCategoryDto;
import com.furkan.karincaa.model.entity.Category;
import com.furkan.karincaa.repository.CategoryRepository;
import com.furkan.karincaa.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    // If category is parent saves directly as a parent
    // If not calls addCategoryToParent to add as subcategory
    @Override
    public RootCategoryDto createRoot(CreateCategoryRequest createCategoryRequest) {
        if(categoryRepository.existsByName(createCategoryRequest.name())){
            throw new AlreadyExistException("Category already exists with name");
        }

        Category category = new Category(createCategoryRequest.name());

        return new RootCategoryDto(categoryRepository.save(category));
    }

    @Override
    public SubCategoryDto addSubCategory(UUID parentId, CreateCategoryRequest createCategoryRequest) {
        if(categoryRepository.existsByName(createCategoryRequest.name())){
            throw new AlreadyExistException("Category already exists with name");
        }
        Category rootCategory = findRootCategoryById(parentId);

        Category category = new Category(createCategoryRequest.name());

        rootCategory.addSubCategory(category);
        categoryRepository.save(rootCategory);

        return new SubCategoryDto(category);
    }

    private Category findRootCategoryById(UUID id) {
        return categoryRepository.findRootCategoryById(id)
                .orElseThrow(() -> new NotFoundException("Parent category not found with id: " + id));
    }

    @Override
    public CategoryDto findById(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));

        return category.getParentCategory() == null
                ? new RootCategoryDto(category)
                : new SubCategoryDto(category);
    }

    @Override
    public Set<CategoryDto> getAllRootCategories() {
        return categoryRepository.getRootCategories()
                .stream()
                .map(CategoryDto::new)
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));

        if (category.getParentCategory() != null) { // If category is subscribed
            category.getParentCategory().removeSubCategory(category);
        }

        categoryRepository.delete(category);
    }
}

package com.furkan.karincaa.model.dto;

import com.furkan.karincaa.model.entity.Category;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class RootCategoryDto extends CategoryDto {
    private final Set<CategoryDto> subCategories;

    public RootCategoryDto(Category category){
        super(category);
        if(category.getParentCategory() != null){
            throw new IllegalArgumentException("Root categories can't have parent");
        }
        this.subCategories = category.getSubCategories().stream().map(CategoryDto::new).collect(Collectors.toSet());
    }
}

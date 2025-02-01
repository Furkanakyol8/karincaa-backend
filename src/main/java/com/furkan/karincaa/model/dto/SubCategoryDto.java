package com.furkan.karincaa.model.dto;

import com.furkan.karincaa.model.entity.Category;
import lombok.Getter;

@Getter
public class SubCategoryDto extends CategoryDto {
    private final CategoryDto parentCategory;
    public SubCategoryDto(Category category){
        super(category);
        if(category.getParentCategory() == null){
            throw new IllegalArgumentException("Sub category should have a parent category");
        }
        this.parentCategory = new CategoryDto(category.getParentCategory());
    }
}

package com.furkan.karincaa.model.dto;

import com.furkan.karincaa.model.entity.Category;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CategoryDto {
    private final UUID id;
    private final String name;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}

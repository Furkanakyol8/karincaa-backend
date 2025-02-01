package com.furkan.karincaa.model.dto;

import java.util.Set;

public record CategoryListResponse(Set<CategoryDto> rootCategories) {
}

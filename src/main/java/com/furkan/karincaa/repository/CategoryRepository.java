package com.furkan.karincaa.repository;

import com.furkan.karincaa.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("""
            SELECT c FROM Category c
                WHERE c.name = :name AND c.parentCategory IS NOT NULL
        """)
    Optional<Category> findSubCategoryByName(String name);
    @Query("""
            SELECT c FROM Category c
                WHERE c.parentCategory IS NULL AND c.id = :id
        """)
    Optional<Category> findRootCategoryById(UUID id);

    @Query("""
            SELECT c FROM Category c
                WHERE c.parentCategory IS NULL
    """)
    Set<Category> getRootCategories();

    boolean existsByName(String name);
}

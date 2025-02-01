package com.furkan.karincaa.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {}, callSuper = true)
@Entity
@Table(name = "categories")
public class Category extends Base {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory; // If null this category is parent.

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Category> subCategories = new HashSet<>();

    public Category(String name){
        this.name = name;
    }

    public void addSubCategory(Category subCategory) {
        if(parentCategory != null) {
            throw new IllegalArgumentException("Category already has parent, cannot have sub categories");
        }

        subCategory.setParentCategory(this);
        subCategories.add(subCategory);
    }
}

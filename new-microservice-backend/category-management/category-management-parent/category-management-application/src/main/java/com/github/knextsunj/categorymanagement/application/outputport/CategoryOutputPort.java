package com.github.knextsunj.categorymanagement.application.outputport;

import com.github.knextsunj.categorymanagement.coredomain.domain.Category;

import java.util.List;

public interface CategoryOutputPort {

    Category existsByCategoryName(String name);

    boolean createCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category updateCategory(Category category,Long id);
}

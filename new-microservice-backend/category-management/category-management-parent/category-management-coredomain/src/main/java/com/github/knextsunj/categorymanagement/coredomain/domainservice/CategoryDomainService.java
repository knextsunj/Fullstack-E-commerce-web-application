package com.github.knextsunj.categorymanagement.coredomain.domainservice;

import com.github.knextsunj.categorymanagement.coredomain.domain.Category;

public class CategoryDomainService {

    public void validateCategoryInfo(Category category) {

        category.getCategoryName().validateCategoryName();
        category.getCategoryName().validateCategoryName();
    }

}

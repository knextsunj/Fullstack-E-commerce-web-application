package com.github.knextsunj.categorymanagement.coredomain.domainservice.impl;

import com.github.knextsunj.categorymanagement.coredomain.domain.Category;
import com.github.knextsunj.categorymanagement.coredomain.domainservice.CategoryDomainService;

public class CategoryDomainServiceImpl implements CategoryDomainService {

    public void validateCategoryInfo(Category category) {

        category.getCategoryName().validateCategoryName();
        category.getCategoryName().validateCategoryName();
    }

}

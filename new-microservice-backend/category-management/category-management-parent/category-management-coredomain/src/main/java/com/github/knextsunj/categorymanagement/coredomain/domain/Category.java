package com.github.knextsunj.categorymanagement.coredomain.domain;

import com.github.knextsunj.categorymanagement.coredomain.exception.CategoryValidationException;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Category {

    private String id;

    private CategoryName categoryName;

    private CategoryDetails categoryDetails;

    public void checkDuplicateCategory(boolean isExists) {
        if (isExists) {
            throw CategoryValidationException.builder().message("Duplicate category received").build();
        }
    }
}

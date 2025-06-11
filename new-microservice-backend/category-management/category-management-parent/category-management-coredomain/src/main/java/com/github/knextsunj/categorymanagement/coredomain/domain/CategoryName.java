package com.github.knextsunj.categorymanagement.coredomain.domain;

import com.github.knextsunj.categorymanagement.coredomain.exception.CategoryValidationException;
import lombok.Value;

import java.util.Objects;

@Value
public class CategoryName {


    private String internalName;

    private String displayName;

    public boolean validateCategoryName() {
        if (Objects.isNull(internalName) || internalName.isBlank()) {
            throw CategoryValidationException.builder().message("Internal name for category is not present").build();
        } else if (Objects.isNull(displayName) || displayName.isBlank()) {
            throw CategoryValidationException.builder().message("Display name for category is not present").build();
        }
        return true;
    }
}

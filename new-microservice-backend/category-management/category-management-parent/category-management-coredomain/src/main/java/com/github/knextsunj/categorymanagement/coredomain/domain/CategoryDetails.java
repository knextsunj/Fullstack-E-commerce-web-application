package com.github.knextsunj.categorymanagement.coredomain.domain;

import com.github.knextsunj.categorymanagement.coredomain.exception.CategoryValidationException;
import lombok.Value;

import java.util.Objects;

@Value
public class CategoryDetails {

    private String description;

    private String imageUrl;

    public boolean validateCategoryDetails() {
        if (Objects.isNull(description) || description.isBlank()) {
            throw CategoryValidationException.builder().message("description for category is not present").build();
        } else if (Objects.isNull(imageUrl) || imageUrl.isBlank()) {
            throw CategoryValidationException.builder().message("imageUrl for category is not present").build();
        }
        return true;
    }
}

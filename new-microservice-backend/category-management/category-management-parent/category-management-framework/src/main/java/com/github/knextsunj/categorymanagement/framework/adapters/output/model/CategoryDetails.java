package com.github.knextsunj.categorymanagement.framework.adapters.output.model;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Access(AccessType.PROPERTY)
public class CategoryDetails {

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String imageUrl;
}

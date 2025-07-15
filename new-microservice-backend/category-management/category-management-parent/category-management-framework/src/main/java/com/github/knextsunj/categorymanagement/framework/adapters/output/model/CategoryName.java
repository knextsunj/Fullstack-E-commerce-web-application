package com.github.knextsunj.categorymanagement.framework.adapters.output.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Access(AccessType.PROPERTY)
public class CategoryName {

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="internal_name_id")
    private CategoryInternalName categoryInternalName;

    @Getter
    @Setter
    private String displayName;
}

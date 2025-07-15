package com.github.knextsunj.categorymanagement.framework.adapters.output.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="category_internal_name")
public class CategoryInternalName {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String name;

    @OneToOne(mappedBy = "categoryInternalName")
    private Category category;
}

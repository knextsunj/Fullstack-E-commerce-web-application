package com.github.knextsunj.categorymanagement.framework.adapters.output.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = Category.CATEGORY_EXISTS,
                query = "select c from category c where c.categoryName.displayName=:displayName"),
        @NamedQuery(name = Category.FIND_CATEGORY_INTERNAL_ID_BY_NAME,
                query = "select cin.id from CategoryInternalName cin where cin.name=:internalName")
})
public class Category {

    public static final String CATEGORY_EXISTS = "categoryExists";

    public static final String FIND_CATEGORY_INTERNAL_ID_BY_NAME = "findCategoryInternalIdByName";

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Getter
    @Setter
    private CategoryName categoryName;

    @Embedded
    @Getter
    @Setter
    private CategoryDetails categoryDetails;

    @Column(name="is_active",columnDefinition = "BOOLEAN DEFAULT true")
    private boolean isActive;

}

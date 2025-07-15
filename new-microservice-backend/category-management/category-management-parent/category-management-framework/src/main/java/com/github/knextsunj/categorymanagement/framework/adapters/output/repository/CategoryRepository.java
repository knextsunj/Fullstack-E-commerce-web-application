package com.github.knextsunj.categorymanagement.framework.adapters.output.repository;

import com.github.knextsunj.categorymanagement.framework.adapters.output.model.Category;
import com.github.knextsunj.categorymanagement.framework.adapters.output.model.CategoryInternalName;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface CategoryRepository extends FullEntityRepository<Category,Long> {

    @Query(named=Category.CATEGORY_EXISTS)
    Category findExistingCategory(@QueryParam("displayName") String categoryName);

    @Query(named=Category.FIND_CATEGORY_INTERNAL_ID_BY_NAME)
    CategoryInternalName findCategoryInternalIdById(@QueryParam("internalName") String internalName);
}

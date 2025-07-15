package com.github.knextsunj.categorymanagement.framework.adapters.mapper;

import com.github.knextsunj.categorymanagement.framework.adapters.input.dto.request.CategoryInputDto;
import com.github.knextsunj.categorymanagement.framework.adapters.output.model.Category;
import com.github.knextsunj.categorymanagement.framework.adapters.output.model.CategoryDetails;
import com.github.knextsunj.categorymanagement.framework.adapters.output.model.CategoryName;
import com.github.knextsunj.categorymanagement.framework.adapters.output.repository.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.AbstractConverter;

import java.util.UUID;

@ApplicationScoped
public class CategoryConvertor {

    private CategoryRepository categoryRepository;

    public AbstractConverter<Category, com.github.knextsunj.categorymanagement.coredomain.domain.Category>
    buildCategoryEntityToDomainConvertor() {
        var abstractConvertor = new AbstractConverter<Category, com.github.knextsunj.categorymanagement.coredomain.domain.Category>() {

            @Override
            protected com.github.knextsunj.categorymanagement.coredomain.domain.Category convert(Category category) {
                com.github.knextsunj.categorymanagement.coredomain.domain.CategoryName categoryName = new
                        com.github.knextsunj.categorymanagement.coredomain.domain.CategoryName(category.getCategoryName().getCategoryInternalName().getName(),
                        category.getCategoryName().getDisplayName());

                com.github.knextsunj.categorymanagement.coredomain.domain.CategoryDetails categoryDetails = new
                        com.github.knextsunj.categorymanagement.coredomain.domain.CategoryDetails(category.getCategoryDetails().getDescription(),
                        category.getCategoryDetails().getImageUrl());

                return com.github.knextsunj.categorymanagement.coredomain.domain.Category.builder().categoryDetails(categoryDetails)
                        .categoryName(categoryName).build();

            }
        };

        return abstractConvertor;

    }

    /**
     * Excludes mapping for category internal name id as that would involve repository call which cannot be done here,Rest of the
     * entity is built here.
     *
     * @return
     */
    public AbstractConverter<com.github.knextsunj.categorymanagement.coredomain.domain.Category, Category>
    buildCategoryDomainToEntityConvertor() {
        var abstractConvertor = new AbstractConverter<com.github.knextsunj.categorymanagement.coredomain.domain.Category, Category>() {

            @Override
            protected Category convert(com.github.knextsunj.categorymanagement.coredomain.domain.Category category) {
                CategoryName categoryName = new CategoryName();
                categoryName.setDisplayName(category.getCategoryName().getDisplayName());
                categoryName.setCategoryInternalName(categoryRepository.findCategoryInternalIdById(category.getCategoryName().getInternalName()));

                CategoryDetails categoryDetails = new CategoryDetails();
                categoryDetails.setDescription(category.getCategoryDetails().getDescription());
                categoryDetails.setImageUrl(category.getCategoryDetails().getImageUrl());

                Category categoryEntity = new Category();
                categoryEntity.setCategoryDetails(categoryDetails);
                categoryEntity.setCategoryName(categoryName);

                return categoryEntity;
            }
        };
        return abstractConvertor;
    }

    public AbstractConverter<CategoryInputDto, com.github.knextsunj.categorymanagement.coredomain.domain.Category> buildCategoryInputDtoToEntityConvertor() {
        var abstractConvertor = new AbstractConverter<CategoryInputDto, com.github.knextsunj.categorymanagement.coredomain.domain.Category>() {

            @Override
            protected com.github.knextsunj.categorymanagement.coredomain.domain.Category convert(CategoryInputDto categoryInputDto) {
                com.github.knextsunj.categorymanagement.coredomain.domain.CategoryDetails categoryDetails = new
                        com.github.knextsunj.categorymanagement.coredomain.domain.CategoryDetails(categoryInputDto.description(), categoryInputDto.imageUrl());
                com.github.knextsunj.categorymanagement.coredomain.domain.CategoryName categoryName = new
                        com.github.knextsunj.categorymanagement.coredomain.domain.CategoryName(categoryInputDto.internalName(), categoryInputDto.displayName());

                return com.github.knextsunj.categorymanagement.coredomain.domain.Category.builder().id(UUID.randomUUID().toString()).categoryDetails(categoryDetails)
                        .categoryName(categoryName).build();
            }
        };
        return abstractConvertor;
    }

    @Inject
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}


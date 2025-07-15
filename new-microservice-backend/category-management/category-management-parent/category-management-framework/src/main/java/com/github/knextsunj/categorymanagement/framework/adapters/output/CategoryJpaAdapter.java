package com.github.knextsunj.categorymanagement.framework.adapters.output;

import com.github.knextsunj.categorymanagement.application.outputport.CategoryOutputPort;
import com.github.knextsunj.categorymanagement.coredomain.exception.CategoryBusinessException;
import com.github.knextsunj.categorymanagement.framework.adapters.output.model.Category;
import com.github.knextsunj.categorymanagement.framework.adapters.output.repository.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.security.spec.ECField;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CategoryJpaAdapter implements CategoryOutputPort {

    private static Logger logger = LogManager.getLogger(CategoryJpaAdapter.class);

    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper;

    @Override
    public com.github.knextsunj.categorymanagement.coredomain.domain.Category existsByCategoryName(String name) {
        Category category = null;
        try {
            category = categoryRepository.findExistingCategory(name);
            return modelMapper.map(category, com.github.knextsunj.categorymanagement.coredomain.domain.Category.class);
        } catch (NoResultException noResultException) {
            logger.error(noResultException);
            logger.info("Incoming category is valid,not existing in system");
        }
        return null;
    }

    @Override
    public boolean createCategory(com.github.knextsunj.categorymanagement.coredomain.domain.Category category) {

        try {
            Category savedCategory = categoryRepository.save(modelMapper.map(category, Category.class));
            return true;
        } catch (Exception ex) {
            logger.error("Unable to save create category", ex);
            throw CategoryBusinessException.builder().message("Unable to save category").throwable(ex).build();
        }
    }

    @Override
    public List<com.github.knextsunj.categorymanagement.coredomain.domain.Category> getAllCategories() {
        List<com.github.knextsunj.categorymanagement.coredomain.domain.Category> resultList = null;
        try {
            resultList = categoryRepository.findAll().stream().map(categoryEntity -> modelMapper.
                    map(categoryEntity, com.github.knextsunj.categorymanagement.coredomain.domain.Category.class)).collect(Collectors.toList());
            return resultList;
        } catch (Exception ex) {
            logger.error("Unable to fetch all categories", ex);
            throw CategoryBusinessException.builder().message("Unable to fetch all categories").throwable(ex).build();
        }
    }

    @Override
    public com.github.knextsunj.categorymanagement.coredomain.domain.Category getCategoryById(Long id) {
        com.github.knextsunj.categorymanagement.coredomain.domain.Category category = null;
        try {
            category = modelMapper.map(categoryRepository.findBy(id), com.github.knextsunj.categorymanagement.coredomain.domain.Category.class);
            return category;
        } catch (Exception ex) {
            logger.error("Unable to fetch requested category", ex);
            throw CategoryBusinessException.builder().message("Unable to fetch requested category").throwable(ex).build();
        }
    }

    @Override
    public com.github.knextsunj.categorymanagement.coredomain.domain.Category updateCategory(com.github.knextsunj.categorymanagement.coredomain.domain.Category category, Long id) {
        com.github.knextsunj.categorymanagement.coredomain.domain.Category updatedCategory = null;
        try {
            Category categoryEntity = categoryRepository.findBy(id);
            modelMapper.map(category, categoryEntity);
            categoryEntity = categoryRepository.save(categoryEntity);
            return modelMapper.map(categoryEntity, com.github.knextsunj.categorymanagement.coredomain.domain.Category.class);
        } catch (Exception ex) {
            logger.error("Unable to update requested category", ex);
            throw CategoryBusinessException.builder().message("Unable to update requested category").throwable(ex).build();
        }
    }

    @Inject
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Inject
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}

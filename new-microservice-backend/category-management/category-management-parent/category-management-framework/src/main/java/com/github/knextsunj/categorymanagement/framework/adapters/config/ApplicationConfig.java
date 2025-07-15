package com.github.knextsunj.categorymanagement.framework.adapters.config;

import com.github.knextsunj.categorymanagement.application.outputport.CategoryOutputPort;
import com.github.knextsunj.categorymanagement.coredomain.domainservice.CategoryDomainService;
import com.github.knextsunj.categorymanagement.coredomain.domainservice.impl.CategoryDomainServiceImpl;
import com.github.knextsunj.categorymanagement.framework.adapters.mapper.CategoryConvertor;
import com.github.knextsunj.categorymanagement.framework.adapters.output.CategoryJpaAdapter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

@ApplicationScoped
public class ApplicationConfig {

    @Inject
    private CategoryConvertor categoryConvertor;

    @Produces
    @PersistenceContext(unitName = "categoryManagementPU")
    private EntityManager entityManager;

    @Produces
    @ApplicationScoped
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.addConverter(categoryConvertor.buildCategoryEntityToDomainConvertor());
        modelMapper.addConverter(categoryConvertor.buildCategoryDomainToEntityConvertor());
        modelMapper.addConverter(categoryConvertor.buildCategoryInputDtoToEntityConvertor());
        return modelMapper;
    }

    @Produces
    @ApplicationScoped
    public CategoryOutputPort categoryOutputPort() {
        CategoryOutputPort categoryOutputPort = new CategoryJpaAdapter();
        return categoryOutputPort;
    }

    @Produces
    @ApplicationScoped
    public CategoryDomainService categoryDomainService() {
        CategoryDomainService categoryDomainService = new CategoryDomainServiceImpl();
        return categoryDomainService;
    }
}

package com.github.knextsunj.categorymanagement.framework.adapters.input.service.impl;

import com.github.knextsunj.categorymanagement.application.outputport.CategoryOutputPort;
import com.github.knextsunj.categorymanagement.coredomain.domain.Category;
import com.github.knextsunj.categorymanagement.coredomain.domainservice.CategoryDomainService;
import com.github.knextsunj.categorymanagement.coredomain.exception.CategoryValidationException;
import com.github.knextsunj.categorymanagement.framework.adapters.input.dto.request.CategoryInputDto;
import com.github.knextsunj.categorymanagement.framework.adapters.input.dto.response.ApiResponseDto;
import com.github.knextsunj.categorymanagement.framework.adapters.input.service.CategoryService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.List;

@ApplicationScoped
public class CategoryServiceImpl implements CategoryService {

    private static Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

    private CategoryOutputPort categoryOutputPort;

    private CategoryDomainService categoryDomainService;

    private ModelMapper modelMapper;

    @Override
    public Response createCategory(CategoryInputDto categoryInputDto) {
        Response response = null;
        try {
            Category category = categoryOutputPort.existsByCategoryName(categoryInputDto.displayName());
            categoryDomainService.checkDuplicateCategory(category);
            boolean isCreated = categoryOutputPort.createCategory(modelMapper.map(categoryInputDto, Category.class));
            ApiResponseDto apiResponseDto =
            ApiResponseDto.builder().isSuccess(true).message("Success creating category")
                    .entity(isCreated).build();
            response = Response.ok(apiResponseDto).build();
        } catch (CategoryValidationException categoryValidationException) {
            logger.error("Category already exists", categoryValidationException);
            ApiResponseDto apiResponseDto =
            ApiResponseDto.builder().isSuccess(false).message("Given category already exists").build();
            response = Response.status(Response.Status.BAD_REQUEST).entity(apiResponseDto).build();
        } catch (Exception ex) {
            logger.error("Category creation failed", ex);
            ApiResponseDto apiResponseDto =
            ApiResponseDto.builder().isSuccess(false).message("Category creation failed").build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiResponseDto).build();
        }
        return response;
    }

    @Override
    public Response getAllCategories() {
        Response response;
        try {
            List<Category> categoryList = categoryOutputPort.getAllCategories();
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().isSuccess(true).message("Success fetching all categories")
                    .entity(categoryList).build();
            response = Response.status(Response.Status.OK).entity(apiResponseDto).build();
        }
        catch(Exception ex) {
            logger.error("Exception when fetching all categories",ex);
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().isSuccess(false).
                    message("Exception when fetching all categories").build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiResponseDto).build();

        }
        return response;
    }

    @Override
    public Response getCategoryById(long id) {
        Response response;
       try {
           Category category = categoryOutputPort.getCategoryById(id);
           categoryDomainService.checkMissingCategory(category);
           ApiResponseDto apiResponseDto = ApiResponseDto.builder().entity(category).
                   message("Successfully fetched category").isSuccess(true).build();
           response = Response.status(Response.Status.OK).entity(apiResponseDto).build();

       } catch (Exception ex) {
           logger.error("Exception when fetching required category",ex);
           ApiResponseDto apiResponseDto =
           ApiResponseDto.builder().isSuccess(false).message("Exception when fetching requested category").build();
           response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiResponseDto).build();

       }
       return response;
    }

    @Override
    public Response editCategory(long id, CategoryInputDto categoryInputDto) {
        Response response;
        try {
            Category category = categoryOutputPort.getCategoryById(id);
            categoryDomainService.checkMissingCategory(category);
            categoryOutputPort.updateCategory(modelMapper.map(categoryInputDto,Category.class),id);
        }
        catch(Exception ex) {

        }
        return null;
    }

    @Inject
    public void setCategoryOutputPort(CategoryOutputPort categoryOutputPort) {
        this.categoryOutputPort = categoryOutputPort;
    }

    @Inject
    public void setCategoryDomainService(CategoryDomainService categoryDomainService) {
        this.categoryDomainService = categoryDomainService;
    }

    @Inject
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}

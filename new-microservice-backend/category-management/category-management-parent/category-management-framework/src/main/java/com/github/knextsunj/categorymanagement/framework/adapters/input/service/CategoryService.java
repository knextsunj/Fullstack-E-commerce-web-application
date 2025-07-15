package com.github.knextsunj.categorymanagement.framework.adapters.input.service;

import com.github.knextsunj.categorymanagement.framework.adapters.input.dto.request.CategoryInputDto;
import jakarta.ws.rs.core.Response;

public interface CategoryService {

    Response createCategory(CategoryInputDto categoryInputDto) ;

    Response getAllCategories();

    Response getCategoryById(long id);

    Response editCategory(long id, CategoryInputDto categoryInputDto);

    Response deleteCategory(long id);
}

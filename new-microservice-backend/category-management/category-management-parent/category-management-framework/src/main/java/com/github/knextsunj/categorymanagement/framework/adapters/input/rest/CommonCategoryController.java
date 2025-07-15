package com.github.knextsunj.categorymanagement.framework.adapters.input.rest;

import com.github.knextsunj.categorymanagement.framework.adapters.input.dto.response.ApiResponseDto;
import com.github.knextsunj.categorymanagement.framework.adapters.input.service.CategoryService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/category")
public class CommonCategoryController {

    private CategoryService categoryService;

    @GET()
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllCategories()  {
        return categoryService.getAllCategories();
    }

    @GET()
    @Path("/get/byId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCategoryById(@PathParam("id") Long id)  {
        return categoryService.getCategoryById(id);
    }

    @Inject
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}




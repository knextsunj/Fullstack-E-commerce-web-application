package com.github.knextsunj.categorymanagement.coredomain.exception;

import lombok.Builder;

@Builder
public class CategoryValidationException extends RuntimeException {

    private Throwable throwable;

    private String message;
}

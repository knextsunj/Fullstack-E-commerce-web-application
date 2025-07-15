package com.github.knextsunj.categorymanagement.coredomain.exception;

import lombok.Builder;
import lombok.Getter;

import javax.print.attribute.standard.MediaSize;

@Builder
public class CategoryBusinessException extends RuntimeException {

    private Throwable throwable;

    private String message;

    public CategoryBusinessException(String message,Throwable throwable) {
        super(message,throwable);
    }

}

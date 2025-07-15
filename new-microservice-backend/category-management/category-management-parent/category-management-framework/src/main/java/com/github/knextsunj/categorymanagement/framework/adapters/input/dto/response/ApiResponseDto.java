package com.github.knextsunj.categorymanagement.framework.adapters.input.dto.response;

import lombok.Builder;

@Builder
public record ApiResponseDto(boolean isSuccess, String message,Object entity) {
}

package com.github.knextsunj.categorymanagement.framework.adapters.input.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;

public record CategoryInputDto(@JsonProperty("displayName") String displayName,
                               @JsonProperty("internalName") String internalName,
                               @JsonProperty("description") String description,
                               @JsonProperty("imageUrl") String imageUrl) {
}

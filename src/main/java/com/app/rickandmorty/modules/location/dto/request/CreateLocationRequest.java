package com.app.rickandmorty.modules.location.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateLocationRequest(
        @NotNull
        @NotBlank
        String name,
        @NotNull
        String type,
        @NotNull
        String dimension
) {}

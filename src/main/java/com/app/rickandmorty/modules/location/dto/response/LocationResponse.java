package com.app.rickandmorty.modules.location.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LocationResponse(
        Long id,
        String name,
        String type,
        String dimension,
        String url,
        LocalDateTime created
) {
}

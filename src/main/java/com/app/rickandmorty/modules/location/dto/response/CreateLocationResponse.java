package com.app.rickandmorty.modules.location.dto.response;

import java.time.LocalDateTime;

public record CreateLocationResponse(
        Long id,
        String name,
        String dimension,
        String url,
        LocalDateTime created
) {
}

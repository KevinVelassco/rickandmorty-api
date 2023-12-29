package com.app.rickandmorty.modules.episode.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record CreateEpisodeResponse(
        Long id,
        String name,
        String episode,
        LocalDate airDate,
        String url,
        LocalDateTime created
) {
}



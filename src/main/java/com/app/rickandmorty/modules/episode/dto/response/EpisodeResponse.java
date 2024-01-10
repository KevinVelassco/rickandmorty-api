package com.app.rickandmorty.modules.episode.dto.response;

import com.app.rickandmorty.modules.episodecharacter.EpisodeCharacter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record EpisodeResponse(
        Long id,
        String name,
        LocalDate airDate,
        String episode,
        String url,
        LocalDateTime created
) {
}

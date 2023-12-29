package com.app.rickandmorty.modules.episode.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CreateEpisodeRequest(
        @NotNull
        @NotBlank
        String name,

        @NotBlank
        String episode,

        @NotNull
        LocalDate airDate
) {
}

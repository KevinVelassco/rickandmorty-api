package com.app.rickandmorty.modules.episode.dto.response;

import com.app.rickandmorty.common.dto.MetaResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record GetAllEpisodesResultsResponse(
        @JsonProperty("info")
        MetaResponse meta,

        @JsonProperty("results")
        List<EpisodeResponse> data
) {}
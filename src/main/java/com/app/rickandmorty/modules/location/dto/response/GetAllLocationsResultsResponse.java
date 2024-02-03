package com.app.rickandmorty.modules.location.dto.response;

import com.app.rickandmorty.common.dto.MetaResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record GetAllLocationsResultsResponse(
        MetaResponse meta,
        List<LocationResponse> data
) {
}

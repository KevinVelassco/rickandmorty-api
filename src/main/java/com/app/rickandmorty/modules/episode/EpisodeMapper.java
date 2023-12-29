package com.app.rickandmorty.modules.episode;

import com.app.rickandmorty.modules.episode.dto.request.CreateEpisodeRequest;
import com.app.rickandmorty.modules.episode.dto.response.CreateEpisodeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EpisodeMapper {
    CreateEpisodeResponse createResponseToDto(Episode episode);
    Episode createRequestToEntity(CreateEpisodeRequest createEpisodeRequest);
}
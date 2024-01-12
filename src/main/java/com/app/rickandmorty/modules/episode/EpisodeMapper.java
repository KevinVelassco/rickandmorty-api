package com.app.rickandmorty.modules.episode;

import com.app.rickandmorty.modules.episode.dto.request.CreateEpisodeRequest;
import com.app.rickandmorty.modules.episode.dto.response.CreateEpisodeResponse;
import com.app.rickandmorty.modules.episode.dto.response.EpisodeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EpisodeMapper {

    @Mapping(target = "url", source = "urlEndpoint")
    @Mapping(target = "created", source = "createdAt")
    CreateEpisodeResponse createResponseToDto(Episode episode);

    Episode createRequestToEntity(CreateEpisodeRequest createEpisodeRequest);

    @Mapping(target = "url", source = "urlEndpoint")
    @Mapping(target = "created", source = "createdAt")
    EpisodeResponse episodeResponseToDto(Episode episode);
}
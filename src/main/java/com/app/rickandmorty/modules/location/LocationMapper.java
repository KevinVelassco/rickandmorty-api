package com.app.rickandmorty.modules.location;

import com.app.rickandmorty.modules.location.dto.request.CreateLocationRequest;
import com.app.rickandmorty.modules.location.dto.response.CreateLocationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(target = "created", source = "createdAt")
    @Mapping(target = "url", source = "urlEndpoint")
    CreateLocationResponse createResponseToDto(Location location);

    Location createRequestToEntity(CreateLocationRequest createLocationRequest);
}

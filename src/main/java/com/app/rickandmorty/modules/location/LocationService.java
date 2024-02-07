package com.app.rickandmorty.modules.location;

import com.app.rickandmorty.common.constants.Constants;
import com.app.rickandmorty.common.dto.MetaResponse;
import com.app.rickandmorty.common.exceptions.NotFoundException;
import com.app.rickandmorty.modules.location.dto.request.CreateLocationRequest;
import com.app.rickandmorty.modules.location.dto.response.CreateLocationResponse;
import com.app.rickandmorty.modules.location.dto.response.GetAllLocationsResultsResponse;
import com.app.rickandmorty.modules.location.dto.response.LocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class LocationService {

    private final LocationMapper locationMapper;

    private final LocationRepository locationRepository;

    public CreateLocationResponse create(CreateLocationRequest createLocationRequest) {
        Location location = locationMapper.createRequestToEntity(createLocationRequest);
        System.out.println(locationMapper.createResponseToDto(location));
            locationRepository.save(location);

            return locationMapper.createResponseToDto(location);
    }


    public GetAllLocationsResultsResponse findAll(String name, String type, String dimension, int page){
        final Pageable pageable = PageRequest.of(page - 1, Constants.LIMIT_RECORDS,
                Sort.by(Sort.Direction.ASC, "id")
        );

        Page<LocationResponse> result = locationRepository
                .findAll(name, type, dimension, pageable)
                .map(location -> LocationResponse
                        .builder()
                        .id(location.getId())
                        .name(location.getName())
                        .type(location.getType())
                        .dimension(location.getDimension())
                        .url(location.getUrlEndpoint())
                        .created(location.getCreatedAt())
                        .build()

                );

        if (result.getContent().isEmpty()) throw new NotFoundException("There is nothing here");

        return GetAllLocationsResultsResponse.builder()
                .meta(MetaResponse.generate(result, page))
                .data(result.getContent())
                .build();
    }


}

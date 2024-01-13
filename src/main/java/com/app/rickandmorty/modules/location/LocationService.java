package com.app.rickandmorty.modules.location;

import com.app.rickandmorty.modules.location.dto.request.CreateLocationRequest;
import com.app.rickandmorty.modules.location.dto.response.CreateLocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

}

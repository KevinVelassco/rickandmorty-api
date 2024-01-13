package com.app.rickandmorty.modules.location;

import com.app.rickandmorty.modules.location.dto.request.CreateLocationRequest;
import com.app.rickandmorty.modules.location.dto.response.CreateLocationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping(path = "api/location")
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public CreateLocationResponse create(@RequestBody @Valid CreateLocationRequest createLocationRequest){
        return  locationService.create(createLocationRequest);
    }
}

package com.app.rickandmorty.modules.location;

import com.app.rickandmorty.modules.location.dto.request.CreateLocationRequest;
import com.app.rickandmorty.modules.location.dto.response.CreateLocationResponse;
import com.app.rickandmorty.modules.location.dto.response.GetAllLocationsResultsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping(path = "api/location")
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public CreateLocationResponse create(@RequestBody @Valid CreateLocationRequest createLocationRequest){
        return  locationService.create(createLocationRequest);
    }

    @GetMapping
    public GetAllLocationsResultsResponse getAll(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "dimension", required = false) String dimension
    ){
        return locationService.findAll(name, type, dimension, page);
    }
}

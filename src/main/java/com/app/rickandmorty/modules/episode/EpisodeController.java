package com.app.rickandmorty.modules.episode;

import com.app.rickandmorty.modules.episode.dto.request.CreateEpisodeRequest;
import com.app.rickandmorty.modules.episode.dto.response.CreateEpisodeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping(path = "api/episode")
public class EpisodeController {

    private final EpisodeService episodeService;

    @PostMapping
    public CreateEpisodeResponse create(@RequestBody @Valid CreateEpisodeRequest createEpisodeRequest) {
        return episodeService.create(createEpisodeRequest);
    }
}

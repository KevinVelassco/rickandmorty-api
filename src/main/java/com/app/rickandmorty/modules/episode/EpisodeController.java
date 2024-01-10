package com.app.rickandmorty.modules.episode;

import com.app.rickandmorty.modules.episode.dto.request.CreateEpisodeRequest;
import com.app.rickandmorty.modules.episode.dto.response.CreateEpisodeResponse;
import com.app.rickandmorty.modules.episode.dto.response.EpisodeResponse;
import com.app.rickandmorty.modules.episode.dto.response.GetAllEpisodesResultsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping(path = "api/episode")
public class EpisodeController {

    private final EpisodeService episodeService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public CreateEpisodeResponse create(@RequestBody @Valid CreateEpisodeRequest createEpisodeRequest) {
        return episodeService.create(createEpisodeRequest);
    }

    @GetMapping
    public GetAllEpisodesResultsResponse getAll(
        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "episode", required = false) String episodeCode

    ) {
        return episodeService.findAll(name, episodeCode, page);
    }

    @GetMapping(path = "{id}")
    public EpisodeResponse getOne(@PathVariable("id") Long id) {
        return episodeService.getOne(id);
    }
}

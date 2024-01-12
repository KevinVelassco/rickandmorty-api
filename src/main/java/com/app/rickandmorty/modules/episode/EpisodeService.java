package com.app.rickandmorty.modules.episode;

import com.app.rickandmorty.modules.episode.dto.request.CreateEpisodeRequest;
import com.app.rickandmorty.modules.episode.dto.response.CreateEpisodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class EpisodeService {

    private final EpisodeRepository episodeRepository;

    private final EpisodeMapper episodeMapper;

    public CreateEpisodeResponse create(CreateEpisodeRequest createEpisodeRequest) {
        Episode episode = episodeMapper.createRequestToEntity(createEpisodeRequest);

        episodeRepository.save(episode);

        return episodeMapper.createResponseToDto(episode);
    }
}
package com.app.rickandmorty.modules.episode;

import com.app.rickandmorty.common.constants.Constants;
import com.app.rickandmorty.common.dto.MetaResponse;
import com.app.rickandmorty.common.exceptions.NotFoundException;
import com.app.rickandmorty.modules.episode.dto.request.CreateEpisodeRequest;
import com.app.rickandmorty.modules.episode.dto.response.CreateEpisodeResponse;
import com.app.rickandmorty.modules.episode.dto.response.EpisodeResponse;
import com.app.rickandmorty.modules.episode.dto.response.GetAllEpisodesResultsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public GetAllEpisodesResultsResponse findAll(String name, String episodeCode, int page) {

        final Pageable pageable = PageRequest.of(page - 1, Constants.LIMIT_RECORDS,
            Sort.by(Sort.Direction.ASC, "id")
        );

        Page<EpisodeResponse> result = episodeRepository
            .findAll(name, episodeCode, pageable)
            .map(episode -> EpisodeResponse
                    .builder()
                    .id(episode.getId())
                    .name(episode.getName())
                    .airDate(episode.getAirDate())
                    .episode(episode.getEpisode())
                    .url(episode.getUrlEndpoint())
                    .created(episode.getCreatedAt())
                    .build()
            );

        if (result.getContent().isEmpty()) throw new NotFoundException("There is nothing here");

        return  GetAllEpisodesResultsResponse.builder()
                .meta(MetaResponse.generate(result, page))
                .data(result.getContent())
                .build();
    }

    public EpisodeResponse getOne(Long id) {
        return episodeRepository.findById(id)
            .map(episodeMapper::episodeResponseToDto)
            .orElseThrow(() -> new NotFoundException("Episode not found"));
    }
}
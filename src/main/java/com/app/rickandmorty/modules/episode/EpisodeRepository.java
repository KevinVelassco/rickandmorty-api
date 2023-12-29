package com.app.rickandmorty.modules.episode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository  extends JpaRepository<Episode, Long> {}

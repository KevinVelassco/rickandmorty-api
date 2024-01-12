package com.app.rickandmorty.modules.episode;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository  extends JpaRepository<Episode, Long> {

    @Query(value = "SELECT * " +
        "FROM episodes " +
        "WHERE ((cast(:name as text)) IS NULL OR name ILIKE %:name%) " +
        "AND ((cast(:episodeCode as text)) IS NULL OR episode ILIKE %:episodeCode%)", nativeQuery = true)
    Page<Episode> findAll(@Param("name") String name, @Param("episodeCode") String episodeCode, Pageable pageable);
}
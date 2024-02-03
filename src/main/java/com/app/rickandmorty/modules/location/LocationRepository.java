package com.app.rickandmorty.modules.location;

import com.app.rickandmorty.modules.episode.Episode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * " +
            "FROM locations"+
            " WHERE ((cast(:name as text)) IS NULL OR name ILIKE %:name%) " +
            "AND ((cast(:type as text)) IS NULL OR type ILIKE %:type%)" +
            "AND ((cast(:dimension as text)) IS NULL OR dimension ILIKE %:dimension%)", nativeQuery = true)
    Page<Location> findAll(@Param("name") String name, @Param("type") String type, @Param("dimension") String dimension, Pageable pageable);
}

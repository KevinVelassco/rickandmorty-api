package com.app.rickandmorty.modules.episode;

import com.app.rickandmorty.modules.episodecharacter.EpisodeCharacter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @SequenceGenerator(
            name = "episode_sequence",
            sequenceName = "episode_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "episode_sequence"
    )
    @Column(updatable = false)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String episode;

    @Column(name = "air_date" , nullable = false)
    private LocalDate airDate;

    @Transient
    private String urlEndpoint;

    public String getUrlEndpoint() {
       return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/api/episode/" + getId();
    }

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "episode")
    private List<EpisodeCharacter> episodeCharacter;
}

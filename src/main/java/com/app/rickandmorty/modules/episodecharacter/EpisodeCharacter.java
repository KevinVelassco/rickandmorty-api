package com.app.rickandmorty.modules.episodecharacter;

import com.app.rickandmorty.modules.character.Character;
import com.app.rickandmorty.modules.episode.Episode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "episode_characters")
public class EpisodeCharacter {
    @Id
    @SequenceGenerator(
            name = "episode_characters_sequence",
            sequenceName = "episode_characters_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "episode_characters_sequence"
    )
    @Column(updatable = false)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="character_id", nullable=false)
    private Character character;

    @ManyToOne
    @JoinColumn(name="episode_id", nullable=false)
    private Episode episode;

}
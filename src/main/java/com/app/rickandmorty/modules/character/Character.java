package com.app.rickandmorty.modules.character;

import com.app.rickandmorty.modules.episodecharacter.EpisodeCharacter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

enum CharacterStatus {
    ALIVE("Alive"), DEAD ("Dead"), UNKNOWN  ("Unknown");
    final String value;
    CharacterStatus(String value) {
        this.value = value;
    }
}

enum CharacterGender {
    FEMALE("Female"), MALE ("Male"), GENDERLESS ("Genderless"), UNKNOWN  ("Unknown");
    final String value;
    CharacterGender(String value) {
        this.value = value;
    }
}

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "characters")
public class Character {
    @Id
    @SequenceGenerator(
            name = "character_sequence",
            sequenceName = "character_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "character_sequence"
    )
    @Column(updatable = false)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CharacterStatus status;

    @Column(length = 100, nullable = false)
    private String species;

    @Column(length = 100)
    private String type;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CharacterGender gender;

    @Column(name = "image_url", length = 300, nullable = false)
    private String imageUrl;

    @Transient
    private String urlEndpoint;

    public String getUrlEndpoint() {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUriString() + "/" + getId();
    }

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "character")
    private List<EpisodeCharacter> episodeCharacter;

}

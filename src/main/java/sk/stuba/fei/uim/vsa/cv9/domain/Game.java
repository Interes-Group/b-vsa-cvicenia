package sk.stuba.fei.uim.vsa.cv9.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Game implements Serializable {

    private static final long serialVersionUID = -7366677726110917410L;

    @EmbeddedId
    private GameId id;

    @ToString.Exclude
    @ManyToOne
    @MapsId("developerId")
    private Developer developer;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Genre> genres;

    @ToString.Exclude
    @ManyToMany(mappedBy = "games", cascade = CascadeType.ALL)
    private List<Publisher> publishers;


    public Game(GameId gameId, Genre... genres) {
        this.id = gameId;
        this.genres = new HashSet<>(Arrays.asList(genres));
    }
}

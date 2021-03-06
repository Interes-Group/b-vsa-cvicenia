package sk.stuba.fei.uim.vsa.cv11.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Publisher implements Serializable {

    private static final long serialVersionUID = 5438586895859339503L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ToString.Exclude
    @ManyToMany
    private List<Game> games;

    public Publisher(String name) {
        this.name = name;
    }

    public void addGame(Game game) {
        if (games == null)
            games = new ArrayList<>();
        games.add(game);
    }

}

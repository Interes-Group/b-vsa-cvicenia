package sk.stuba.fei.uim.vsa.cv9.domain;

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
public class Developer implements Serializable {

    private static final long serialVersionUID = 2289590339062908943L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "developer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Game> games;

    public Developer(String name) {
        this.name = name;
    }

    public void addGame(Game game) {
        if (games == null)
            games = new ArrayList<>();
        games.add(game);
    }
}

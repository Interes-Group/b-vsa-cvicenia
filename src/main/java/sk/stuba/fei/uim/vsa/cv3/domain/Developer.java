package sk.stuba.fei.uim.vsa.cv3.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Developer implements Serializable {

    private static final long serialVersionUID = 4823417357668565956L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "DEVELOPER_ID")
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

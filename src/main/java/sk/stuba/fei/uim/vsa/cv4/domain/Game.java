package sk.stuba.fei.uim.vsa.cv4.domain;

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

    private static final long serialVersionUID = 2996702055645581511L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Genre> genres;

    @ToString.Exclude
    @ManyToMany
    private List<Publisher> publishers;

    @ToString.Exclude
    @ManyToOne
    private Developer developer;


    public Game(String name, Genre... genres) {
        this.name = name;
        this.genres = new HashSet<>(Arrays.asList(genres));
    }
}

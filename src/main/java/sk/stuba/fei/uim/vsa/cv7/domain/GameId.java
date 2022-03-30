package sk.stuba.fei.uim.vsa.cv7.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GameId implements Serializable {

    private static final long serialVersionUID = 1463424730961795991L;

    private Long developerId;

    @Column(name = "GAME_NAME")
    private String name;

    public GameId() {
    }

    public GameId(Long developer, String name) {
        this.developerId = developer;
        this.name = name;
    }

    public Long getDeveloper() {
        return developerId;
    }

    public void setDeveloper(Long developerId) {
        this.developerId = developerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameId gameId = (GameId) o;

        if (developerId != null ? !developerId.equals(gameId.developerId) : gameId.developerId != null) return false;
        return name != null ? name.equals(gameId.name) : gameId.name == null;
    }

    @Override
    public int hashCode() {
        int result = developerId != null ? developerId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GameId{" +
                "order=" + developerId +
                ", name='" + name + '\'' +
                '}';
    }
}

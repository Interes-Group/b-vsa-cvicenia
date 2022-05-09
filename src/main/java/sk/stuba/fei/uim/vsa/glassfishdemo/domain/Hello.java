package sk.stuba.fei.uim.vsa.glassfishdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Hello implements Serializable {
    private static final long serialVersionUID = 8193684536671820467L;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Hello() {
    }

    public Hello(String name) {
        this.name = name;
    }

    public String helloString() {
        return "Hello " + name + "!";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

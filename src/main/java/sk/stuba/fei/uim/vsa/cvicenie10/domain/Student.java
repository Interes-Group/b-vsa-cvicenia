package sk.stuba.fei.uim.vsa.cvicenie10.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String programme;
    private Degree degree;

    public Student() {
    }

    public Student(String name, String programme, Degree degree) {
        this.name = name;
        this.programme = programme;
        this.degree = degree;
    }
}

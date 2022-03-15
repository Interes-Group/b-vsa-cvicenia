package sk.stuba.fei.uim.vsa.cv2;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@NamedNativeQuery(name = Person.FIND_ALL, query = "select * from PERSON", resultClass = Person.class)
@NamedQuery(name = Person.FIND_BY_ID, query = "select p from Person p where p.id = :id")
public class Person {

    public static final String FIND_ALL = "Person.findAll";
    public static final String FIND_BY_ID = "Person.findById";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer age;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    public Person(String name, Integer age, Date dateOfBirth) {
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
    }
}

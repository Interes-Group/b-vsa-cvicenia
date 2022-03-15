package sk.stuba.fei.uim.vsa.cv2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class Cvicenie2 {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("Milan");
        person.setAge(30);
        person.setDateOfBirth(new Date());

        save(person);

        //TypedQuery<Person> query = manager.createNamedQuery(Person.FIND_BY_ID, Person.class);
        //query.setParameter("id",1L);
        //query.getResultStream().forEach(System.out::println);
    }

    static void save(Object... entities) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = managerFactory.createEntityManager();

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        for (Object entity : entities) {
            manager.persist(entity);
        }
        transaction.commit();
    }

}

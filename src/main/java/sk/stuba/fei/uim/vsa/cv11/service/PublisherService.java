package sk.stuba.fei.uim.vsa.cv11.service;

import sk.stuba.fei.uim.vsa.cv11.domain.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class PublisherService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public List<Publisher> getAllPublishers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Publisher> query = em.createQuery("select p from Publisher p", Publisher.class);
        List<Publisher> ps = query.getResultList();
        em.close();
        return ps;
    }

    public Publisher createPublisher(Publisher publisher) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(publisher);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        }
        em.close();
        return publisher;
    }


}

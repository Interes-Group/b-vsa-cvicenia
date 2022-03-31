package sk.stuba.fei.uim.vsa.cv7.service;

import sk.stuba.fei.uim.vsa.cv7.domain.Developer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DeveloperService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public Developer findByName(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Developer> query = em.createQuery("select d from Developer d where d.name=" + name, Developer.class);
        Developer dev = query.getSingleResult();
        em.close();
        return dev;
    }

    public Developer save(Developer developer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(developer);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        }
        em.close();
        return developer;
    }

}

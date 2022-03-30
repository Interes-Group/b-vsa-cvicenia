package sk.stuba.fei.uim.vsa.cv7.service;

import sk.stuba.fei.uim.vsa.cv7.domain.Game;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class GameService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public List<Game> getAllGames() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Game> query = em.createQuery("select g from Game g", Game.class);
        List<Game> games = query.getResultList();
        em.close();
        return games;
    }

    public Game findGameByName(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Game> query = em.createQuery("select g from Game g where g.id.name=:name", Game.class);
        query.setParameter("name", name);
        Game game = query.getSingleResult();
        em.close();
        return game;
    }

    public Game saveGame(Game game) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(game);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        }
        em.close();
        return game;
    }

    public void close() {
        emf.close();
    }

}

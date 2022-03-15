package sk.stuba.fei.uim.vsa.cv4;

import sk.stuba.fei.uim.vsa.cv4.domain.Developer;
import sk.stuba.fei.uim.vsa.cv4.domain.Game;
import sk.stuba.fei.uim.vsa.cv4.domain.Genre;
import sk.stuba.fei.uim.vsa.cv4.domain.Publisher;

import javax.persistence.*;
import java.util.Collections;
import java.util.Optional;

public class Cvicenie4 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

//        Game game = new Game("World of Warcraft", Genre.RPG, Genre.MMO);
//        Publisher publisher = new Publisher("Microsoft");
//        Developer developer = new Developer("Blizzard Entertainment");
//        game.setPublishers(Collections.singletonList(publisher));
//        game.setDeveloper(developer);
//        developer.addGame(game);
//        persist(emf, game, developer, publisher);

        EntityManager manager = emf.createEntityManager();

//        TypedQuery<Developer> devQuery = manager.createQuery("select d from Developer d where d.id = :id", Developer.class);
//        devQuery.setParameter("id", 2L);
//        devQuery.getResultStream().forEach(d -> System.out.println(d.getGames().size()));
        Developer dev = manager.find(Developer.class,2L);
        System.out.println(dev.getGames().stream().findFirst().get());

        manager.close();
        emf.close();
    }

    public static void persist(EntityManagerFactory emf, Object... entities) {
        EntityManager manager = emf.createEntityManager();

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        for (Object entity : entities) {
            manager.persist(entity);
        }
        transaction.commit();
        manager.close();
    }

    public static void pridajHru(String nazov, String meno) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = managerFactory.createEntityManager();

        Publisher publisher = null;
        Game game = null;

        Query gameQuery = manager.createNativeQuery("SELECT * from GAME where NAME = '" + nazov + "'", Game.class);
        Optional<Game> g = gameQuery.getResultStream().findFirst();
        if (g.isPresent())
            return;

        Query publisherQuery = manager.createNativeQuery("SELECT * from PUBLISHER where NAME='" + meno + "'", Publisher.class);
        Optional<Publisher> pub = publisherQuery.getResultStream().findFirst();
        if (!pub.isPresent()) {
            publisher = new Publisher(meno);
//            persist(publisher);
        } else {
            publisher = pub.get();
        }

        game = new Game(nazov);
        game.setPublishers(Collections.singletonList(publisher));
//        persist(game);

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(game);
        manager.persist(publisher);
        transaction.commit();
    }

}

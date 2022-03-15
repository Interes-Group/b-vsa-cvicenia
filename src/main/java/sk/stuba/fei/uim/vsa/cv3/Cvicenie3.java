package sk.stuba.fei.uim.vsa.cv3;

import sk.stuba.fei.uim.vsa.cv3.domain.Game;
import sk.stuba.fei.uim.vsa.cv3.domain.Publisher;

import javax.persistence.*;
import java.util.Collections;
import java.util.Optional;

public class Cvicenie3 {

    public static void main(String[] args) {
//        Game game = new Game("World of Warcraft", Genre.RPG, Genre.MMO);
//        Publisher publisher = new Publisher("Blizzard Entertainment");
//        Developer developer = new Developer("Blizzard Entertainment");
//        game.setPublishers(Collections.singletonList(publisher));
////        game.setDeveloper(developer);
//        developer.addGame(game);
//        persist(game, publisher, developer);
        pridajHru("WoW", "Blizzard");
    }

    public static void persist(Object... entities) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = managerFactory.createEntityManager();

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        for (Object entity : entities) {
            manager.persist(entity);
        }
        transaction.commit();
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

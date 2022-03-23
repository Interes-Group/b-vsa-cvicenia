package sk.stuba.fei.uim.vsa.cv5;

import sk.stuba.fei.uim.vsa.cv5.domain.*;

import javax.persistence.*;

public class Cvicenie5 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        Developer developer = new Developer("Blizzard Entertainment");
        persist(emf, developer);
        Game game = new Game(new GameId(developer.getId(), "WoW"), Genre.RPG, Genre.MMO);
        Publisher publisher = new Publisher("Microsoft");
        game.setDeveloper(developer);
        publisher.addGame(game);
        persist(emf, game, publisher);


        // Is remove cascading?
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
//        developer = em.find(Developer.class, developer.getId());
//        em.remove(developer);
        game = em.find(Game.class, game.getId());
        game.getPublishers().clear();
        em.merge(game);
        publisher = em.find(Publisher.class, publisher.getId());
        em.remove(publisher);
        em.getTransaction().commit();
        em.close();

//

//        game.setPublishers(Collections.singletonList(publisher));
//        game.setDeveloper(developer);
//        developer.addGame(game);

//        EntityManager manager = emf.createEntityManager();
//
//        TypedQuery<Developer> devQuery = manager.createQuery("select d from Developer d where d.id = :id", Developer.class);
//        devQuery.setParameter("id", 2L);
//        devQuery.getResultStream().forEach(d -> System.out.println(d.getGames().size()));

//        manager.close();
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

}

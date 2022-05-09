package sk.stuba.fei.uim.vsa.glassfishdemo;

import sk.stuba.fei.uim.vsa.glassfishdemo.domain.Hello;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Stateless
@Path("/hello")
public class HelloResource {

    @PersistenceContext(unitName = "punit")
    private EntityManager em;

    @GET
    @Produces("text/plain")
    public String hello(@QueryParam("name") String name) throws InterruptedException {
        if (name == null)
            name = "Application";
        Hello hello = new Hello(name);
        em.persist(hello);
        Thread.sleep(300);

        TypedQuery<Hello> query = em.createQuery("select h from Hello h where h.name='" + name + "'", Hello.class);
        Hello h1 = query.getSingleResult();
        if (h1 == null)
            throw new IllegalStateException("Hello entity not found!");
        return h1.helloString();
    }

}

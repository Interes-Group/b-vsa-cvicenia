package sk.stuba.fei.uim.vsa.cv7;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import sk.stuba.fei.uim.vsa.cv7.web.GameResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Cvicenie7Application extends Application {

    static final Set<Class<?>> appClasses = new HashSet<>();

    static {
        appClasses.add(GameResource.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return appClasses;
    }
}

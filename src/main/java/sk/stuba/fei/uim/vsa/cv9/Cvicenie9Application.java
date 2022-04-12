package sk.stuba.fei.uim.vsa.cv9;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import sk.stuba.fei.uim.vsa.cv9.web.CORSFilter;
import sk.stuba.fei.uim.vsa.cv9.web.DeveloperResource;
import sk.stuba.fei.uim.vsa.cv9.web.GameResource;
import sk.stuba.fei.uim.vsa.cv9.web.PublisherResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Cvicenie9Application extends Application {

    static final Set<Class<?>> appClasses = new HashSet<>();

    static {
        appClasses.add(GameResource.class);
        appClasses.add(DeveloperResource.class);
        appClasses.add(PublisherResource.class);
        appClasses.add(CORSFilter.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return appClasses;
    }
}

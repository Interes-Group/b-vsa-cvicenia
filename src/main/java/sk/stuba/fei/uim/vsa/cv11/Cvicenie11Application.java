package sk.stuba.fei.uim.vsa.cv11;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import sk.stuba.fei.uim.vsa.cv11.web.CORSFilter;
import sk.stuba.fei.uim.vsa.cv11.web.DeveloperResource;
import sk.stuba.fei.uim.vsa.cv11.web.GameResource;
import sk.stuba.fei.uim.vsa.cv11.web.PublisherResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Cvicenie11Application extends Application {

    static final Set<Class<?>> appClasses = new HashSet<>();

    static {
        appClasses.add(GameResource.class);
        appClasses.add(DeveloperResource.class);
        appClasses.add(PublisherResource.class);
        appClasses.add(CORSFilter.class);
        appClasses.add(BasicAuthRequestFilter.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return appClasses;
    }
}

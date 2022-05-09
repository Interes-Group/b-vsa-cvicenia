package sk.stuba.fei.uim.vsa.cv11;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@PreMatching
@Provider
public class BasicAuthRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
            String auth = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);
            if(auth == null)
                ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }
}

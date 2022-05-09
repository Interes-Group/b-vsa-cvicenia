package sk.stuba.fei.uim.vsa.cv11.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.cv11.domain.Publisher;
import sk.stuba.fei.uim.vsa.cv11.service.PublisherService;
import sk.stuba.fei.uim.vsa.cv11.web.response.MessageDto;

import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

@Path("/publisher")
public class PublisherResource {
    private static final Logger LOGGER = Logger.getLogger(PublisherResource.class.getName());

    public static final String EMPTY_RESPONSE = "{}";

    private final PublisherService publisherService = new PublisherService();
    private final ObjectMapper json = new ObjectMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization,
                         @QueryParam("name") String name) {
        LOGGER.info("Provided email: " + getEmail(authorization));
        LOGGER.info("Provided param: " + name);
        List<Publisher> publishers = publisherService.getAllPublishers();
        try {
            return json.writeValueAsString(publishers);
        } catch (JsonProcessingException e) {
            try {
                return json.writeValueAsString(MessageDto.buildError(e.getMessage()));
            } catch (JsonProcessingException ex) {
                // ignore
                return EMPTY_RESPONSE;
            }
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String body) {
        try {
            Publisher p = json.readValue(body, Publisher.class);
            p = publisherService.createPublisher(p);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(json.writeValueAsString(p))
                    .build();
        } catch (JsonProcessingException e) {
            return Response.noContent().build();
        }
    }

    private String getEmail(String authHeader) {
        String base64Encoded = authHeader.substring("Basic ".length());
        String decoded = new String(Base64.getDecoder().decode(base64Encoded));
        return decoded.split(":")[0];
    }


}

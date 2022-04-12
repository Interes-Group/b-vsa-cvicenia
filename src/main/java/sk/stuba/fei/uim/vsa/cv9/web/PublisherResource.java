package sk.stuba.fei.uim.vsa.cv9.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.cv9.domain.Publisher;
import sk.stuba.fei.uim.vsa.cv9.service.PublisherService;
import sk.stuba.fei.uim.vsa.cv9.web.response.MessageDto;

import java.util.List;

@Path("/publisher")
public class PublisherResource {

    public static final String EMPTY_RESPONSE = "{}";

    private final PublisherService publisherService = new PublisherService();
    private final ObjectMapper json = new ObjectMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
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


}

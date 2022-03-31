package sk.stuba.fei.uim.vsa.cv7.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import sk.stuba.fei.uim.vsa.cv7.domain.Developer;
import sk.stuba.fei.uim.vsa.cv7.service.DeveloperService;
import sk.stuba.fei.uim.vsa.cv7.web.response.DeveloperDto;
import sk.stuba.fei.uim.vsa.cv7.web.response.factory.DeveloperResponseFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/developer")
public class DeveloperResource {

    public static final String EMPTY_RESPONSE = "{}";

    private static final Logger LOGGER = Logger.getLogger(DeveloperResource.class.getName());

    private final ObjectMapper json = new ObjectMapper();
    private final DeveloperService service = new DeveloperService();
    private final DeveloperResponseFactory factory = new DeveloperResponseFactory();

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDeveloper(@PathParam("name") String name) {
        if (name == null) return EMPTY_RESPONSE;
        Developer dev = service.findByName(name);
        DeveloperDto dto = factory.transformToDto(dev);
        try {
            return json.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createDeveloper(String developer) {
        try {
            DeveloperDto dto = json.readValue(developer, DeveloperDto.class);
            Developer dev = service.save(factory.transformToEntity(dto));
            dto = factory.transformToDto(dev);
            return json.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

}

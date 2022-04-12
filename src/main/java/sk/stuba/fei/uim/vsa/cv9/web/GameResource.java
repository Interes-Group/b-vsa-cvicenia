package sk.stuba.fei.uim.vsa.cv9.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import sk.stuba.fei.uim.vsa.cv9.domain.Game;
import sk.stuba.fei.uim.vsa.cv9.service.GameService;
import sk.stuba.fei.uim.vsa.cv9.web.response.GameDto;
import sk.stuba.fei.uim.vsa.cv9.web.response.factory.GameResponseFactory;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/game")
public class GameResource {

    public static final String EMPTY_RESPONSE = "{}";

    private static final Logger LOGGER = Logger.getLogger(GameResource.class.getName());

    private final ObjectMapper json = new ObjectMapper();
    private final GameService service = new GameService();
    private final GameResponseFactory factory = new GameResponseFactory();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getGames() {
        List<Game> games = service.getAllGames();
        List<GameDto> gameDtos = games.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            return json.writeValueAsString(gameDtos);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGame(@PathParam("name") String name) {
        if (name == null) return EMPTY_RESPONSE;
        Game game = service.findGameByName(name);
        GameDto gameDto = factory.transformToDto(game);
        try {
            return json.writeValueAsString(gameDto);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postGame(String requestBody) {
        try {
            GameDto dto = json.readValue(requestBody, GameDto.class);
            Game game = service.saveGame(factory.transformToEntity(dto));
            dto = factory.transformToDto(game);
            return json.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

}

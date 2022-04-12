package sk.stuba.fei.uim.vsa.cv9.web.response.factory;

import sk.stuba.fei.uim.vsa.cv9.domain.*;
import sk.stuba.fei.uim.vsa.cv9.web.response.GameDto;

import java.util.List;
import java.util.stream.Collectors;

public class GameResponseFactory implements ResponseFactory<Game, GameDto> {

    @Override
    public GameDto transformToDto(Game entity) {
        GameDto dto = new GameDto();
        dto.setName(entity.getId().getName());
        dto.setGenres(entity.getGenres().stream().map(Genre::toString).collect(Collectors.toSet()));
        dto.setDeveloper(new DeveloperResponseFactory().transformToDto(entity.getDeveloper()));
        PublisherResponseFactory factory = new PublisherResponseFactory();
        dto.setPublishers(entity.getPublishers().stream().map(factory::transformToDto).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public Game transformToEntity(GameDto dto) {
        Game game = new Game();
        game.setId(new GameId(null, dto.getName()));
        game.setGenres(dto.getGenres().stream().map(g -> Genre.valueOf(g.toUpperCase())).collect(Collectors.toSet()));
        PublisherResponseFactory publisherResponseFactory = new PublisherResponseFactory();
        List<Publisher> pubs = dto.getPublishers().stream().map(publisherResponseFactory::transformToEntity).collect(Collectors.toList());
        game.setPublishers(pubs);
        DeveloperResponseFactory developerResponseFactory = new DeveloperResponseFactory();
        Developer dev = developerResponseFactory.transformToEntity(dto.getDeveloper());
        game.setDeveloper(dev);
        game.getId().setDeveloper(dev.getId());
        return game;
    }
}

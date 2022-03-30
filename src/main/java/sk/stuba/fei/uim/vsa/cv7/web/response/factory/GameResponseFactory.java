package sk.stuba.fei.uim.vsa.cv7.web.response.factory;

import sk.stuba.fei.uim.vsa.cv7.domain.Game;
import sk.stuba.fei.uim.vsa.cv7.domain.Genre;
import sk.stuba.fei.uim.vsa.cv7.web.response.GameDto;

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
        return null;
    }
}

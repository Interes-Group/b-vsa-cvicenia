package sk.stuba.fei.uim.vsa.cv9.web.response.factory;

import sk.stuba.fei.uim.vsa.cv9.domain.Developer;
import sk.stuba.fei.uim.vsa.cv9.web.response.DeveloperDto;

public class DeveloperResponseFactory implements ResponseFactory<Developer, DeveloperDto> {
    @Override
    public DeveloperDto transformToDto(Developer entity) {
        DeveloperDto dto = new DeveloperDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    @Override
    public Developer transformToEntity(DeveloperDto dto) {
        Developer dev = new Developer();
        dev.setId(dto.getId());
        dev.setName(dto.getName());
        return dev;
    }
}

package sk.stuba.fei.uim.vsa.cv7.web.response.factory;

import sk.stuba.fei.uim.vsa.cv7.domain.Publisher;
import sk.stuba.fei.uim.vsa.cv7.web.response.PublisherDto;

public class PublisherResponseFactory implements ResponseFactory<Publisher, PublisherDto> {
    @Override
    public PublisherDto transformToDto(Publisher entity) {
        PublisherDto dto = new PublisherDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    @Override
    public Publisher transformToEntity(PublisherDto dto) {
        Publisher pub = new Publisher();
        pub.setId(dto.getId());
        pub.setName(dto.getName());
        return pub;
    }
}

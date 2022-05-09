package sk.stuba.fei.uim.vsa.cv11.web.response.factory;

import sk.stuba.fei.uim.vsa.cv11.web.response.Dto;

public interface ResponseFactory<R, T extends Dto> {

    T transformToDto(R entity);

    R transformToEntity(T dto);

}

package com.cakmak.language.model.mapper.base;


import com.cakmak.language.model.dto.base.BaseDto;
import com.cakmak.language.model.dto.base.BaseRequest;
import com.cakmak.language.model.dto.base.BaseResponse;
import com.cakmak.language.model.entity.base.BaseEntity;

import java.util.List;

public interface BaseMapper<Req extends BaseRequest, Dto extends BaseDto, E extends BaseEntity, Res extends BaseResponse> {
	
	Dto requestToDto(Req req);
	Req dtoToRequest(Dto reqDto);
	Dto responseToDto(Res res);
	Res dtoToResponse(Dto resDto);
	Dto entityToDto(E entity);
	E dtoToEntity(Dto dto);
	List<Dto> entityListToDtoList(List<E> entityList);
	List<E> dtoListToEntityList(List<Dto> dtoList);
	List<Dto> requestListToDtoList(List<Req> requestList);
	List<Res> dtoListToResponseList(List<Dto> dtoList);
}
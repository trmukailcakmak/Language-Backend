package com.cakmak.language.model.mapper.base;

import com.cakmak.language.model.dto.base.BaseDto;
import com.cakmak.language.model.dto.base.BaseRequest;
import com.cakmak.language.model.dto.base.BaseResponse;
import com.cakmak.language.model.entity.base.BaseEntity;
import com.cakmak.language.repository.base.BaseRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapper<Req extends BaseRequest, Dto extends BaseDto, E extends BaseEntity, Res extends BaseResponse> implements BaseMapper<Req, Dto, E, Res> {
	

	@Autowired protected BaseRepository<E> repository;
	@Autowired protected BaseMapper<Req, Dto, E, Res> mapper;

	protected abstract Dto mapRequestToDto(Req req);
	protected abstract Res mapDtoToResponse(Dto dto);
	protected abstract Dto mapEntityToDto(E entity);
	protected abstract E mapDtoToEntity(Dto dto);


	@Override
	public Dto requestToDto(Req req) {
		if (req == null) return null;

		return mapRequestToDto(req);
	}
	@Override
	public Res dtoToResponse(Dto dto) {
		if (dto == null) return null;

		return mapDtoToResponse(dto);
	}

	@Override
	@SneakyThrows
	public E dtoToEntity(Dto dto) {
		if (dto == null) return null;

		return mapDtoToEntity(dto);
	}


	@Override
	public Dto entityToDto(E entity) {
		if (entity == null) return null;
		return mapEntityToDto(entity);
	}

	@Override
	public List<Dto> entityListToDtoList(List<E> entities) {
		if (entities == null) return null;
		List<Dto> dtos = new ArrayList<>();
		for (E entity : entities) {
			dtos.add(mapEntityToDto(entity));
		}
		return dtos;
	}

	@Override
	public List<E> dtoListToEntityList(List<Dto> dtos) {
		if (dtos == null) return null;
		List<E> entities = new ArrayList<>();
		for (Dto dto : dtos) {
			entities.add(mapDtoToEntity(dto));
		}
		return entities;
	}

	@Override
	public List<Dto> requestListToDtoList(List<Req> requests) {
		if (requests == null) return null;
		List<Dto> dtos = new ArrayList<>();
		for (Req req : requests) {
			dtos.add(mapRequestToDto(req));
		}
		return dtos;
	}

	@Override
	public List<Res> dtoListToResponseList(List<Dto> dtos) {
		if (dtos == null) return null;
		List<Res> res = new ArrayList<>();
		for (Dto dto : dtos) {
			res.add(mapDtoToResponse(dto));
		}
		return res;
	}
}

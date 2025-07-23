package com.cakmak.language.service.base;


import com.cakmak.language.model.dto.base.BaseDto;
import com.cakmak.language.model.dto.base.BaseRequest;
import com.cakmak.language.model.dto.base.BaseResponse;
import com.cakmak.language.model.entity.base.BaseEntity;

import java.util.List;

public interface BaseService<Req extends BaseRequest,Dto extends BaseDto,E extends BaseEntity, Res extends BaseResponse> {
	Dto save(Dto dto) throws Exception;
	Dto update(Dto dto) throws Exception;

	Dto findById(Long id) throws Exception;

	Dto deleteById(Long id) throws Exception;

	List<Dto> getAll() throws Exception;

	//Page<Dto> getAllPageable() throws Exception;
}
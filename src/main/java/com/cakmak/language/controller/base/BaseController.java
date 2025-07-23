package com.cakmak.language.controller.base;

import com.cakmak.language.model.dto.base.BaseDto;
import com.cakmak.language.model.dto.base.BaseRequest;
import com.cakmak.language.model.dto.base.BaseResponse;
import com.cakmak.language.model.entity.base.BaseEntity;
import com.cakmak.language.constant.EndPointConstant;
import com.cakmak.language.model.mapper.base.BaseMapper;
import com.cakmak.language.service.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

public abstract class BaseController<Req extends BaseRequest, Dto extends BaseDto, E extends BaseEntity, Res extends BaseResponse> {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	@Autowired protected BaseService<Req, Dto, E, Res> service;
	@Autowired protected BaseMapper<Req, Dto, E, Res> mapper;

	@PostMapping(EndPointConstant.CREATE)
	public ResponseEntity<Res> save(@RequestBody Req req) throws Exception {
		logger.info(String.valueOf(req));
		Dto dto = service.save(mapper.requestToDto(req));
		Res res = mapper.dtoToResponse(dto);
		logger.info(String.valueOf(res));
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping(EndPointConstant.UPDATE)
	public ResponseEntity<?> update(@RequestBody Req req) throws Exception {
		logger.info(String.valueOf(req));
		Dto dto = service.update(mapper.requestToDto(req));
		Res res = mapper.dtoToResponse(dto);
		logger.info(String.valueOf(res));
		return ResponseEntity.ok(res);
	}

	/*@GetMapping(EndPointConstant.GET_ALL_PAGEABLE)
	@ApiOperation("GET ALL DATA")
	public ResponseEntity<List<Res>> getAllPageable(Pageable pageable) throws Exception {
		Page<Dto> dtos = service.getAllPageable();
		List<Res> ress = mapper.dtoListToResponseList(dtos);
		return ResponseEntity.ok(ress);
	}*/

	@GetMapping(EndPointConstant.GET_ALL)
	public ResponseEntity<List<Res>> getAll() throws Exception {
		List<Dto> dtos = service.getAll();
		List<Res> ress = mapper.dtoListToResponseList(dtos);
		return ResponseEntity.ok(ress);
	}

	@GetMapping(EndPointConstant.GET_BY_ID)
	public ResponseEntity<?> getById(@PathVariable("id") Long id) throws Exception {
		Dto dto = service.findById(id);
		if (Objects.isNull(dto)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
		}
		Res res = mapper.dtoToResponse(dto);
		return ResponseEntity.ok(res);
	}

	@DeleteMapping(value = EndPointConstant.DELETE_BY_ID)
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) throws Exception {
		logger.info(String.valueOf(id));
		Dto dto = service.deleteById(id);
		Res res = mapper.dtoToResponse(dto);
		logger.info(String.valueOf(res));
		return ResponseEntity.ok(res);
	}

}
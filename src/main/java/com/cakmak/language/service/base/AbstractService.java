package com.cakmak.language.service.base;

import com.cakmak.language.constant.MessageKey;
import com.cakmak.language.exceptions.LanguageException;
import com.cakmak.language.model.constant.RecordStatus;
import com.cakmak.language.model.dto.base.BaseDto;
import com.cakmak.language.model.dto.base.BaseRequest;
import com.cakmak.language.model.dto.base.BaseResponse;
import com.cakmak.language.model.entity.base.BaseEntity;
import com.cakmak.language.model.mapper.base.BaseMapper;
import com.cakmak.language.repository.base.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Transactional(rollbackFor = Throwable.class)
public abstract class AbstractService<Req extends BaseRequest,Dto extends BaseDto,E  extends BaseEntity, Res extends BaseResponse> implements BaseService<Req, Dto, E, Res> {

	@PersistenceContext protected EntityManager em;
	@Autowired protected BaseRepository<E> repository;
	@Autowired ApplicationContext applicationContext;
	@Autowired protected BaseMapper<Req, Dto, E, Res> mapper;
	@Autowired protected MessageSource messageSource;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	@Override
	public Dto save(Dto dto) throws Exception {
		saveValidation(dto);

		E save = saveData(mapper.dtoToEntity(dto));

		return mapper.entityToDto(save);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Dto update(Dto dto) throws Exception {
		updateValidationTakeDtoAsParameter(dto);

		E save = saveData(mapper.dtoToEntity(dto));

		return mapper.entityToDto(save);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Dto findById(Long id) throws Exception {
		Optional<E> entity = findDataById(id);
		if (!entity.isPresent()) {
			return null;
		}

		return mapper.entityToDto(entity.get());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Dto deleteById(Long id) throws Exception {
		Optional<E> entity = findDataById(id);
		if (!entity.isPresent()) {
			throwException(MessageKey.ERR02);
		}
		entity.get().setRecordStatus(RecordStatus.PASSIVE);
		E save = saveData(entity.get());
		return mapper.entityToDto(save);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Dto> getAll() throws Exception {
		return mapper.entityListToDtoList(findAllData());
	}
	public E saveData(E e) {
		E save = repository.save(e);
		return save;
	}

	private void updateValidationTakeDtoAsParameter(Dto dto) throws Exception {
		if (Objects.isNull(dto.getId())) {
			throwException(MessageKey.ERR10);
		}
		Optional<E> entity = findDataById(dto.getId());
		if (Objects.isNull(entity) || !entity.isPresent()) {
			throwException(MessageKey.ERR11);
		}
	}
	private void saveValidation(Dto dto) {
		if (Objects.nonNull(dto.getId())) {
			throwException(MessageKey.ERR09);
		}
	}

	private void throwException(String errCode) {
		String message = this.messageSource.getMessage(errCode, null, Locale.ENGLISH);
		throw new LanguageException(errCode, message);
	}

	private Optional<E> findDataById(Long id) {
		return repository.findByIdAndRecordStatus(id, RecordStatus.ACTIVE);
	}

	private List<E> findAllData() {
		return repository.findAllByRecordStatus(RecordStatus.ACTIVE);
	}

}

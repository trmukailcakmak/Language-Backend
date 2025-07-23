package com.cakmak.language.repository.base;

import com.cakmak.language.model.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> {

    Optional<E> findByIdAndRecordStatus(Long id, Integer recordStatus);

    List<E> findAllByRecordStatus(Integer recordStatus);


}
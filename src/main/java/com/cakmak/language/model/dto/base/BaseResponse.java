package com.cakmak.language.model.dto.base;


import java.io.Serializable;

public interface BaseResponse extends Serializable {

	Long getId();
	void setId(Long id);

	Integer getRecordStatus();
	void setRecordStatus(Integer recordStatus);

}

package com.cakmak.language.model.dto.base;


import java.io.Serializable;
import java.util.Date;

public interface BaseDto extends Serializable {

	Long getId();
	void setId(Long id);
	Date getCreDate();
	void setCreDate(Date createdDate);

	String getCreBy();
	void setCreBy(String createdUser);

	Integer getRecordStatus();
	void setRecordStatus(Integer recordStatus);

}

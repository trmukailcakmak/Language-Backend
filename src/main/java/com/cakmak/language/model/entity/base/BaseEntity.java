package com.cakmak.language.model.entity.base;


import java.io.Serializable;
import java.util.Date;

public interface BaseEntity extends Serializable {

	Long getId();
	void setId(Long id);

	Date getCreDate();
	void setCreDate(Date creDate);

	String getCreBy();
	void setCreBy(String creBy);

	Date getModDate();
	void setModDate(Date modDate);

	String getModBy();
	void setModBy(String modBy);

	Integer getRecordStatus();
	void setRecordStatus(Integer recordStatus);

}

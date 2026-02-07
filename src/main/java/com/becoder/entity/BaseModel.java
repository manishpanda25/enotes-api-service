package com.becoder.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
	
	@Column(name="is_active")
	private boolean active;
	
	@Column(name="is_deleted")
	private boolean deleted;
	private Integer createdBy;
	private Date createdOn;
	private Integer updatedBy;
	private Date updatedOn;

	

}

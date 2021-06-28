package com.bookshop.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@Column(name = "createddate")
	@CreatedDate
	protected Date createdDate;
	
	@Column(name = "modifieddate")
	@LastModifiedDate
	protected Date modifiedDate;
	
	@Column(name = "createdby")
	@CreatedBy
	protected String createdBy;
	
	@Column(name = "modifiedby")
	@LastModifiedBy
	protected String modifiedBy;


	public Date getCreatedDate() {
		return createdDate;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}
}

package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AudtingEntity {

	  @CreatedDate
	  private LocalDateTime createddate;
	  
	  @CreatedBy
	  private String createdBy;
	  
	  @LastModifiedBy
	  private String updatedBy;
	  
	  @LastModifiedDate
	  private LocalDateTime updateDate;

	  public LocalDateTime getCreateddate() {
		  return createddate;
	  }

	  public void setCreateddate(LocalDateTime createddate) {
		  this.createddate = createddate;
	  }

	  public String getCreatedBy() {
		  return createdBy;
	  }

	  public void setCreatedBy(String createdBy) {
		  this.createdBy = createdBy;
	  }

	  public String getUpdatedBy() {
		  return updatedBy;
	  }

	  public void setUpdatedBy(String updatedBy) {
		  this.updatedBy = updatedBy;
	  }

	  public LocalDateTime getUpdateDate() {
		  return updateDate;
	  }

	  public void setUpdateDate(LocalDateTime updateDate) {
		  this.updateDate = updateDate;
	  }
	  
}

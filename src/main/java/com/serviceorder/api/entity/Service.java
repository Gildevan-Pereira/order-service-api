package com.serviceorder.api.entity;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Service extends BaseEntity {

	private Integer serviceCategoryId;
	private String title;
	private String description;
	private float amount;
	private String remarks;
	
	
}

package com.serviceorder.api.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ServiceCategory extends BaseEntity {
	
	private String name;
	
}

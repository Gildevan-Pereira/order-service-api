package com.serviceorder.api.entity;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Technician extends BaseEntity{
	
	private String fullname;
	private String idantity;
	private String phone;
	private String email;
	private String role;
	private float commission;
	private Integer address_id;
	
	public Technician() {
		
	}
}

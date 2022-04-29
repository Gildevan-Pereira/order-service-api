package com.serviceorder.api.entity;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client extends BaseEntity{
	
	private String fullname;
	private String idantity;
	private String phone;
	private String email;
	private Integer address_id;
	
	public Client() {
		
	}
	
}

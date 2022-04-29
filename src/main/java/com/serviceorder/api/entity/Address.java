package com.serviceorder.api.entity;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address extends BaseEntity{
	
	private String street;
	private String number;
	private String district;
	private String zipcode;
	private String city;
	private String state;
	private String complement;
	
	public Address() {
		
	}
}

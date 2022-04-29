package com.serviceorder.api.entity;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client extends BaseEntity{

	private Integer addressId;
	

}

package com.serviceorder.api.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class Agent extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String fullname;
	private String identity;
	private String phone;
	private String email;

}

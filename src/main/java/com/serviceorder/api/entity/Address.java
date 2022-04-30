package com.serviceorder.api.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "address")
public class Address extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String street;
	private String number;
	private String district;
	private String zipcode;
	private String city;
	private String state;
	private String complement;
	
}

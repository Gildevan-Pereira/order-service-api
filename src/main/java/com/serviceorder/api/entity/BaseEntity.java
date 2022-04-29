package com.serviceorder.api.entity;

import java.time.LocalDateTime;
import java.util.UUID;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity extends Agent{
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private UUID uuid;
	private LocalDateTime createdAt;
	private LocalDateTime removedAt;
	
	
}
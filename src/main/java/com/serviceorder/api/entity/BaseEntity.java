package com.serviceorder.api.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity {
	@Id
	private Integer id;
	@Id
	private UUID uuid;
	private LocalDateTime created_at;
	private LocalDateTime removed_at;
	
	public BaseEntity() {
		
	}
	
	
}
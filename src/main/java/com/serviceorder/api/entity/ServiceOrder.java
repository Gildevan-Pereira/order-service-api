package com.serviceorder.api.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ServiceOrder extends BaseEntity{
	
	private Integer serviceCategoryIid;
	private Integer clientId;
	private Integer technicianId;
	private LocalDateTime startedAt;
	private LocalDateTime finishedAt;
	private String remarks;
	
}

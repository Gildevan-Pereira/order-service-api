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
	
	private Integer service_category_id;
	private Integer client_id;
	private Integer technician_id;
	private LocalDateTime started_at;
	private LocalDateTime finished_at;
	private String remarks;
	
	public ServiceOrder() {
		
	}
}

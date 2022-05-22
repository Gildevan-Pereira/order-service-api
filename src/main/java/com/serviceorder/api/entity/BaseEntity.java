package com.serviceorder.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty(value = "id")
	@Column(updatable = false)
	private UUID uid;
	
	@Column(name = "created_at", updatable = false, insertable = false)
	private LocalDateTime createdAt;
	
	@JsonIgnore
	@Column(name = "removed_at", insertable = false)
	private LocalDateTime removedAt;
	
	@JsonIgnore
	@PrePersist  //mesmo que o banco não tenha a capacidade de gerar automaticamente, esta anotação irá gerar automaticamente
	private void generateUid () {
		this.uid = UUID.randomUUID();
	}
}
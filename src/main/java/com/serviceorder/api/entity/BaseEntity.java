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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(updatable = false) //Nunca pode ser mudado TODO:Só pra lembrar FIXME: Só pra lembra	
	protected UUID uid;
	
	@Column(name = "created_at", updatable = false, insertable = false)
	protected LocalDateTime createdAt;
	
	@Column(name = "removed_at", updatable = false, insertable = false)
	protected LocalDateTime removedAt;
	
	@PrePersist  //mesmo que o banco não tenha a capacidade de gerar automaticamente, esta anotação irá gerar automaticamente
	private void generateUid () {
		this.uid = UUID.randomUUID();
	}
	
}
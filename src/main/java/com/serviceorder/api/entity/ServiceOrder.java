package com.serviceorder.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(schema = "public", name = "service_order")
public class ServiceOrder extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OneToOne(fetch = FetchType.EAGER) //Define estratégias para buscar dados do banco de dados. A estratégia EAGER é um requisito no tempo de execução do provedor de persistência de que os dados devem ser buscados avidamente. A estratégia LAZY é uma dica para o tempo de execução do provedor de persistência de que os dados devem ser buscados lentamente quando forem acessados ​​pela primeira vez. A implementação tem permissão para buscar dados para os quais a dica de estratégia LAZY foi especificada.
	@JoinColumn(name = "service_id")
	private Service service;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id")
	private Client client;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "technician_id")
	private Technician technician;
	
	@Column(name = "started_at", updatable = false)
	private LocalDateTime startedAt;
	
	@Column(name = "finished_at", updatable = false)
	private LocalDateTime finishedAt;
	
	private String remarks;
	
	
	public LocalDateTime setStartedAt(LocalDateTime setStartedAt) {
		return this.startedAt = setStartedAt;
	}
	public LocalDateTime setFinishedAt(LocalDateTime finishedAt) {
		return this.finishedAt = finishedAt;
	}
	
	
}

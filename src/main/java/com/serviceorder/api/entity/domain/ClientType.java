package com.serviceorder.api.entity.domain;

import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum ClientType {

	FISICO(1, "Pessoa Física"),
	JURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String description;
	
	private ClientType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	
	public static ClientType fromCode(int code) {
		return Stream.of(ClientType.values())
				.filter(item -> item.getCod() == code)
				.findFirst()
				.get();
	}
}

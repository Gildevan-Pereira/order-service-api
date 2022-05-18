package com.serviceorder.api.exceptionhanler.err;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Problema {

	private Integer status;
	private LocalDateTime dataHora;
	private String title;
}

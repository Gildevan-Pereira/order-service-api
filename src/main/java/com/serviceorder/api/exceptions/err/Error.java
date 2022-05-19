package com.serviceorder.api.exceptions.err;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class Error {

	private Integer httpStatus;
	private LocalDateTime dateTime;
	private Integer code;
	private String message;
	private List<Field> fields;
	
	
	@Getter
	@AllArgsConstructor
	public static class Field {
		private String name;
		private Integer code;
		private String message;
		
	}


	public Error(Integer code, String message) {
		this.code = code;
		this.message = message;
		this.dateTime = LocalDateTime.now();
	}


	public static Error parse(String message) {
		String[] array = message.split("#");
		Integer code = Integer.parseInt(array[0]);
		String errMessage = array[1];
		return new Error(code, errMessage);
	}
		
}

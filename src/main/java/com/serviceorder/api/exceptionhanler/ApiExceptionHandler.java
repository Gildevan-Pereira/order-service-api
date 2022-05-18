package com.serviceorder.api.exceptionhanler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.serviceorder.api.exceptionhanler.err.Problema;

@ControllerAdvice   //Specific purpose to handle exceptions globally
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Problema problema = new Problema() {};
		problema.setStatus(status.value());
		problema.setDataHora(LocalDateTime.now());
		problema.setTitle("Um ou mais campos estão inválidos. Preencha corretamente e tente novamente!");

		return handleExceptionInternal(ex, problema, headers, status, request);
	}
}

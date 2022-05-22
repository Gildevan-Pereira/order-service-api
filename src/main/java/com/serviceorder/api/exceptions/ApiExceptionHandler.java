package com.serviceorder.api.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.serviceorder.api.exceptions.dto.ErrorDTO;
import com.serviceorder.api.message.Messages;

@ControllerAdvice // Specific purpose to handle exceptions globally
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ErrorDTO.Field> field = new ArrayList<>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			String[] array = message.split("#");
			Integer code = Integer.parseInt(array[0]);
			String errMessage = array[1];
			field.add(new ErrorDTO.Field(name, code, errMessage));
		}
		
		ErrorDTO errorDTO = new ErrorDTO(null, "Invalid or null fields were found");
		errorDTO.setFields(field);
		errorDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		return handleExceptionInternal(ex, errorDTO, headers, status, request);
	}

	@ResponseBody
	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public Map<String, List<ErrorDTO>> handleCustomException(CustomException ex) {
		return errors(ErrorDTO.parse(ex.getMessage()));
	}

	@ResponseBody
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, List<ErrorDTO>> handleGenericException(Exception ex) {
		return errors(ErrorDTO.parse(Messages.GENERIC_ERROR));
	}
	
	private Map<String, List<ErrorDTO>> errors(ErrorDTO errorDTO) {
		return Collections.singletonMap("errors", Arrays.asList(errorDTO));
	}
}


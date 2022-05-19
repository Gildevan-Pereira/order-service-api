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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.serviceorder.api.exceptions.err.Error;
import com.serviceorder.api.message.Messages;

@ControllerAdvice // Specific purpose to handle exceptions globally
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error.Field> field = new ArrayList<>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			String[] array = message.split("#");
			Integer code = Integer.parseInt(array[0]);
			String errMessage = array[1];
			field.add(new Error.Field(name, code, errMessage));
		}

		Error error = new Error(null, "Invalid or null fields were found");
		error.setFields(field);
		error.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		return handleExceptionInternal(ex, error, headers, status, request);
	}

	@ResponseBody
	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public Map<String, List<Error>> handleCustomException(CustomException ex) {
		return errors(Error.parse(ex.getMessage()));
	}

	private Map<String, List<Error>> errors(Error error) {
		return Collections.singletonMap("errors", Arrays.asList(error));
	}

	// path parameter type validation
	@ResponseBody
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, List<Error>> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException ex) {
		return errors((Error) Arrays.asList(Error.parse(
				String.format(Messages.SERVICE_ORDER_INVALID, ex.getName(), ex.getRequiredType().getSimpleName()))));
	}
}


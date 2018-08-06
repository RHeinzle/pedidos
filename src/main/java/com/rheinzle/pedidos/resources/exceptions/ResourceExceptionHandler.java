package com.rheinzle.pedidos.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rheinzle.pedidos.services.exceptions.DataIntegrityException;
import com.rheinzle.pedidos.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(httpStatus.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(httpStatus).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrityException(DataIntegrityException e, HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(httpStatus.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(httpStatus).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ValidationError err = new ValidationError(httpStatus.value(), "Erro de validação", System.currentTimeMillis());

		for (FieldError fe : e.getBindingResult().getFieldErrors()) {
			err.addFieldMessage(fe.getField(), fe.getDefaultMessage());
		}

		return ResponseEntity.status(httpStatus).body(err);
	}

}

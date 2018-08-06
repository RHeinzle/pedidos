package com.rheinzle.pedidos.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationError extends StandardError {

	public ValidationError(Integer status, String message, Long timeStamp) {
		super(status, message, timeStamp);
	}

	List<FieldMessage> fieldMessages = new ArrayList<>();

	public void addFieldMessage(String fieldName, String message) {
		fieldMessages.add(new FieldMessage(fieldName, message));
	}

}

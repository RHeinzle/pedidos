package com.rheinzle.pedidos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandardError {

	private Integer status;
	private String message;
	private Long timeStamp;

}

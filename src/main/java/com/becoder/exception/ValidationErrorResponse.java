package com.becoder.exception;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ValidationErrorResponse {

	private int status;
	private String error;
	private Map<String, String> messages;

	public ValidationErrorResponse(int status, String error, Map<String, String> messages) {
		this.status = status;
		this.error = error;
		this.messages = messages;
	}

}

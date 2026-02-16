package com.becoder.util;

import org.apache.commons.io.FilenameUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.becoder.handler.GenericResponse;

public class CommonUtill {

	public static ResponseEntity<?> createBuildResponse(Object data, HttpStatus status) {
		GenericResponse response = GenericResponse.builder().respStatus(status).status("success").message("success").data(data)
				.build();
		return response.create();
	}

	public static ResponseEntity<?> createBuildResponseMessage( String message, HttpStatus status) {
		GenericResponse response = GenericResponse.builder().respStatus(status).status("success").message(message)
				.build();
		return response.create();
	}
	
	public static ResponseEntity<?> createErrorResponse(Object data, HttpStatus status) {
		GenericResponse response = GenericResponse.builder().respStatus(status).status("failed").message("failed")
				.build();
		return response.create();
	}
	
	public static ResponseEntity<?> createErrorResponseMessage(String message, HttpStatus status) {
		GenericResponse response = GenericResponse.builder().respStatus(status).status("failed").message(message)
				.build();
		return response.create();
	}

	public static  String getContentType(String originalFileName) {
		
		String extension = FilenameUtils.getExtension(originalFileName);
		switch(extension) {
		case "pdf":
			return "application/pdf";
		case "txt":
			return "application/txt";
		case "png":
			return "image/png";
		case "jpeg":
			return "image/jpeg";
			default :
				return "application/octet-stream";
		}
		
	}
}

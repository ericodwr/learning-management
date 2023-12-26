package com.ocire.lms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ocire.lms.dto.ErrorResDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorResDto> handleNullPointerException(NullPointerException npe) {
		npe.printStackTrace();
		final ErrorResDto errorResult = new ErrorResDto();
		errorResult.setMessage(npe.getMessage());
		
		return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

package com.HospitalManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(StaffServiceException.class)
	public ResponseEntity<String> staffServiceException(StaffServiceException exception) {

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(StaffNotFoundException.class)
	public ResponseEntity<String> staffNotFoundException(StaffNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DoctorServiceException.class)
	public ResponseEntity<String> DoctorServiceException(DoctorServiceException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<String> doctorNotFoundException(DoctorNotFoundException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

}

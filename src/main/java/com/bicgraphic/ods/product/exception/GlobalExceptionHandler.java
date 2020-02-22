package com.bicgraphic.ods.product.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bicgraphic.ods.product.beans.Status;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Status> genericExceptionHandler(Exception exception) {
		Status status = new Status(HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getMessage());
		return new ResponseEntity<Status>(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = ODSProductException.class)
	public ResponseEntity<Status> ExceptionHandler(ODSProductException exception) {
		Status status = new Status(exception.getCustomerErrorCode(), exception.getCustomerErrorMessage());
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		List<String> validationErrorList = new ArrayList<String>();
		for (FieldError error : errors) {
			validationErrorList.add(error.getDefaultMessage());
		}
		Status errorDetails = new Status("Validation Failed", validationErrorList.toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}

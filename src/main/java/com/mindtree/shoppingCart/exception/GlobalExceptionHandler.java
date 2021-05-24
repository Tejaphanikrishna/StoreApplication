package com.mindtree.shoppingCart.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ShoppingCartAppException.class)
	public ResponseEntity<?> handleDepartmentManageException(ShoppingCartAppException e, WebRequest wr) {
		ErrorDetails ed = new ErrorDetails(new Date(), e.getMessage(), wr.getDescription(false));
		return new ResponseEntity<>(ed,HttpStatus.BAD_REQUEST);
	}
}

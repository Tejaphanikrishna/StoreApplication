package com.mindtree.shoppingCart.exception.daoException;

import com.mindtree.shoppingCart.exception.serviceException.CartServiceException;

public class InvalidCartException extends CartServiceException {

	public InvalidCartException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidCartException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidCartException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidCartException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidCartException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

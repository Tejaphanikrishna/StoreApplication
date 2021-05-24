package com.mindtree.shoppingCart.service;

import org.springframework.stereotype.Service;

import com.mindtree.shoppingCart.exception.serviceException.CartServiceException;
import com.mindtree.shoppingCart.model.Cart;

@Service
public interface CartService{
		
	public Cart getCartById(int id) throws  CartServiceException;
	
	public Cart addCart(Cart s,int id) throws CartServiceException;
	
}


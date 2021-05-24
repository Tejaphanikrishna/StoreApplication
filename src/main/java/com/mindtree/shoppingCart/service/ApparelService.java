package com.mindtree.shoppingCart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindtree.shoppingCart.exception.serviceException.CartServiceException;
import com.mindtree.shoppingCart.model.Apparel;

@Service
public interface ApparelService {

	public List<Apparel> getAllProducts() throws CartServiceException;

	public Apparel addApparel(Apparel product,int id)throws CartServiceException;

}

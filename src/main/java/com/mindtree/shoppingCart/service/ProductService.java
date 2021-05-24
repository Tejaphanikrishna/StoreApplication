package com.mindtree.shoppingCart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindtree.shoppingCart.exception.serviceException.CartServiceException;
import com.mindtree.shoppingCart.model.Product;

@Service
public interface ProductService {

	public List<Product> getAllProducts() throws CartServiceException;
	
	public Product getProduct(int id) throws CartServiceException;

	public boolean deleteById(int id)throws CartServiceException;
	
	public Product updateProduct(Product p, int id)throws CartServiceException;

	public boolean deleteByCartId(int cartid)throws CartServiceException;

	public Product getProductByName(String name)throws CartServiceException;

}

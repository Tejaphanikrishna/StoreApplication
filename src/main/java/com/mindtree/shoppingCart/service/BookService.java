package com.mindtree.shoppingCart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindtree.shoppingCart.exception.serviceException.CartServiceException;
import com.mindtree.shoppingCart.model.Book;

@Service
public interface BookService {

	public List<Book> getAllBook() throws CartServiceException;
	
	public Book addBook(Book product,int id)throws CartServiceException;
}

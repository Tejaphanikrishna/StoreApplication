package com.mindtree.shoppingCart.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingCart.exception.daoException.InvalidProductException;
import com.mindtree.shoppingCart.exception.serviceException.CartServiceException;
import com.mindtree.shoppingCart.model.Book;
import com.mindtree.shoppingCart.model.Cart;
import com.mindtree.shoppingCart.model.Product;
import com.mindtree.shoppingCart.repository.BookRepo;
import com.mindtree.shoppingCart.repository.CartRepo;
import com.mindtree.shoppingCart.service.BookService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private CartRepo cartRepo;

	//adding book product to BOok DB
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Book addBook(Book product, int id) throws CartServiceException {

		Book pass;
		try {
			Cart per = cartRepo.findById(id)
					.orElseThrow(() -> new InvalidProductException("Cart with id:" + id + " not found"));
			pass = null;

			int quan = 0;

			pass = bookRepo.findByName(product.getProductName()).orElse(null);

			Set<Product> li = per.getProducts();
			System.out.println(li);

			if (pass == null) {
				product.setQuantity(1);
				pass = bookRepo.save(product);
			} else {
				quan = pass.getQuantity();
				pass.setQuantity(quan + 1);
				pass = bookRepo.save(pass);
			}
			per.setProducts(li);
			pass.setCart(per);
			per = cartRepo.save(per);

		} catch (InvalidProductException e) {
			throw new CartServiceException(e.getMessage(), e);
		}
		System.out.println("Added Details");
		return pass;

	}

	//getting all products from Book DB
	@Override
	public List<Book> getAllBook() throws CartServiceException {
		List<Book> li;
		try {
			li = new ArrayList<>();
			bookRepo.findAll().forEach(li::add);
		} catch (RuntimeException e) {
			throw new CartServiceException(e.getMessage(), e);
		}
		return li;
	}

}

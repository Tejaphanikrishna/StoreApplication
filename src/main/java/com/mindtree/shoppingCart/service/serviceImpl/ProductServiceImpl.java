package com.mindtree.shoppingCart.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingCart.exception.daoException.InvalidCartException;
import com.mindtree.shoppingCart.exception.daoException.InvalidProductException;
import com.mindtree.shoppingCart.exception.serviceException.CartServiceException;
import com.mindtree.shoppingCart.model.Cart;
import com.mindtree.shoppingCart.model.Product;
import com.mindtree.shoppingCart.repository.CartRepo;
import com.mindtree.shoppingCart.repository.ProductRepo;
import com.mindtree.shoppingCart.service.ProductService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CartRepo cartRepo;

	//getting a product by id
	@Override
	public Product getProduct(int id) throws CartServiceException {
		Product result = null;

		try {
			result = productRepo.findById(id).orElseThrow(() -> new InvalidProductException("Product ID not found"));
		} catch (InvalidProductException e) {
			throw new CartServiceException("Cant get Product", e);
		}
		return result;

	}

	//getting all products to Product DB
	@Override
	public List<Product> getAllProducts() throws CartServiceException {
		List<Product> li;
		try {
			li = new ArrayList<>();
			productRepo.findAll().forEach(li::add);
		} catch (RuntimeException e) {
			throw new CartServiceException("cant get all details", e);
		}
		return li;
	}

	//deleting product by ID in Product DB
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean deleteById(int id) throws CartServiceException {
		Product result = null;
		try {
			result = productRepo.findById(id).orElseThrow(() -> new InvalidProductException("Product ID not found"));
			
			productRepo.delete(result);
			return true;
		} catch (InvalidProductException e) {
			throw new CartServiceException("Cant get Product", e);
		}
	}

	//updating product quantity to Product DB
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Product updateProduct(Product p, int id) throws CartServiceException {
		Product pass;
		try {
			pass = null;

			pass = productRepo.findById(id).orElseThrow(() -> new InvalidProductException("Product not found"));

			int quan = p.getQuantity();

			if (quan == 0) {
				productRepo.deleteById(id);
			} else if (quan < 0) {
				throw new InvalidProductException("Invalid Product entry");
			} else {
				pass.setQuantity(p.getQuantity());
				productRepo.save(pass);
			}
		} catch (InvalidProductException e) {
			throw new CartServiceException(e.getMessage(), e);
		}
		System.out.println("Added Details");
		return pass;

	}

	//deleting list of products of a cart to Product DB
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean deleteByCartId(int cartid) throws CartServiceException {

		try {
			Cart per = cartRepo.findById(cartid)
					.orElseThrow(() -> new InvalidCartException("Cart with id:" + cartid + " not found"));

			productRepo.deleteAll(per.getProducts());
			return true;

		} catch (InvalidCartException e) {
			throw new CartServiceException(e.getMessage(), e);
		}
	}

	//getting product by name from Product DB
	@Override
	public Product getProductByName(String name) throws CartServiceException {
		Product result = null;

		try {
			result = productRepo.findByName(name).orElseThrow(() -> new InvalidProductException("Product ID not found"));
		} catch (InvalidProductException e) {
			throw new CartServiceException("Cant get Product", e);
		}
		return result;

	}

}

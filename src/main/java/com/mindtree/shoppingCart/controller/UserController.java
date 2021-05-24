package com.mindtree.shoppingCart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingCart.exception.ShoppingCartAppException;
import com.mindtree.shoppingCart.exception.serviceException.CartServiceException;
import com.mindtree.shoppingCart.model.Apparel;
import com.mindtree.shoppingCart.model.Book;
import com.mindtree.shoppingCart.model.Cart;
import com.mindtree.shoppingCart.model.Product;
import com.mindtree.shoppingCart.service.ApparelService;
import com.mindtree.shoppingCart.service.BookService;
import com.mindtree.shoppingCart.service.CartService;
import com.mindtree.shoppingCart.service.ProductService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ProductService productService;

	@Autowired
	private BookService bookService;

	@Autowired
	private ApparelService apparelService;

	@Autowired
	private CartService cartService;
	
//	 create a cart so that you can add products using other endpoints
	@RequestMapping(method = RequestMethod.POST, value = "/addCart/{id}")
	public ResponseEntity<Cart> addCart(@RequestBody Cart c,@PathVariable int id) throws ShoppingCartAppException {
		try {
			return new ResponseEntity<>(cartService.addCart(c, id), HttpStatus.ACCEPTED);
		} catch (CartServiceException e) {
			throw new ShoppingCartAppException(e.getMessage(), e);
		}
	}
	
	// add product to cart and increment quantity accordingly
	@RequestMapping(method = RequestMethod.POST, value = "/addBook/{id}")
	public ResponseEntity<Product> addBook(@RequestBody Book c, @PathVariable int id) throws ShoppingCartAppException {
		try {
			return new ResponseEntity<>(bookService.addBook(c, id), HttpStatus.ACCEPTED);
		} catch (CartServiceException e) {
			throw new ShoppingCartAppException(e.getMessage(), e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addApparel/{id}")
	public ResponseEntity<Product> addApparel(@RequestBody Apparel c, @PathVariable int id)
			throws ShoppingCartAppException {
		try {
			return new ResponseEntity<>(apparelService.addApparel(c, id), HttpStatus.ACCEPTED);
		} catch (CartServiceException e) {
			throw new ShoppingCartAppException(e.getMessage(), e);
		}
	}
	
	// update quantity of product in cart(if quantity is zero remove it)
	@RequestMapping(method = RequestMethod.PATCH, value = "/updateProduct/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product p, @PathVariable int id)
			throws ShoppingCartAppException {
		try {
			return new ResponseEntity<>(productService.updateProduct(p, id), HttpStatus.ACCEPTED);
		} catch (CartServiceException e) {
			throw new ShoppingCartAppException(e.getMessage(), e);
		}
	}

	// view products in the cart and the amount to be paid
	@GetMapping("/getCartData/{id}")
	public ResponseEntity<Cart> getCartData(@PathVariable int id) throws ShoppingCartAppException{
		try {
			return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.ACCEPTED);
		} catch (CartServiceException e) {
			throw new ShoppingCartAppException(e.getMessage(), e);
		}
	}


	// remove one or all products
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable int id) throws ShoppingCartAppException {
		try {
			return new ResponseEntity<>(productService.deleteById(id), HttpStatus.ACCEPTED);
		} catch (CartServiceException e) {
			throw new ShoppingCartAppException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/deleteProductByCart/{cartid}")
	public ResponseEntity<Boolean> deleteCart(@PathVariable int cartid) throws ShoppingCartAppException {
		try {
			return new ResponseEntity<>(productService.deleteByCartId(cartid), HttpStatus.ACCEPTED);
		} catch (CartServiceException e) {
			throw new ShoppingCartAppException(e.getMessage(), e);
		}
	}


	// search product by id
	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) throws ShoppingCartAppException {
		try {
			return new ResponseEntity<>(productService.getProduct(id), HttpStatus.ACCEPTED);
		} catch (CartServiceException e) {
			throw new ShoppingCartAppException(e.getMessage(), e);
		}
	}


	// search product by category
	@GetMapping("/getProductByName/{name}")
	public ResponseEntity<Product> getProductsByName(@PathVariable String name)
			throws ShoppingCartAppException {
		try {
			return new ResponseEntity<>(productService.getProductByName(name), HttpStatus.ACCEPTED);
		} catch (CartServiceException e) {
			throw new ShoppingCartAppException(e.getMessage(), e);
		}
	}


	// search product by category
	@GetMapping("/getProductByCategory/{category}")
	public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String category) throws ShoppingCartAppException {
		try {
			if(category.equalsIgnoreCase("Book")) {
				List<Product> li = new ArrayList<>(bookService.getAllBook());
				return new ResponseEntity<>(li, HttpStatus.ACCEPTED);		
			}
			else if(category.equalsIgnoreCase("Apparel"))  {
				List<Product> li = new ArrayList<>(apparelService.getAllProducts());
				return new ResponseEntity<>(li, HttpStatus.ACCEPTED);
			}
			else
				throw new CartServiceException("Invalid Product Category");
		} catch (CartServiceException e) {
			throw new ShoppingCartAppException(e.getMessage(), e);
		}
	}

}

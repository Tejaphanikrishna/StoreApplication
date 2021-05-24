package com.mindtree.shoppingCart.service.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingCart.exception.daoException.InvalidCartException;
import com.mindtree.shoppingCart.exception.serviceException.CartServiceException;
import com.mindtree.shoppingCart.model.Cart;
import com.mindtree.shoppingCart.model.Product;
import com.mindtree.shoppingCart.model.User;
import com.mindtree.shoppingCart.repository.CartRepo;
import com.mindtree.shoppingCart.repository.UserRepo;
import com.mindtree.shoppingCart.service.CartService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private UserRepo userRepo;

	//getting cart details by id
	@Override
	public Cart getCartById(int id) throws CartServiceException {
		Cart result = null;

		try {
			result = cartRepo.findById(id).orElseThrow(() -> new InvalidCartException("Cart ID not found"));
			float total = result.getTotalAmount();

			Set<Product> list = result.getProducts();

//			List<Float> price = new ArrayList<>();
//			List<Integer> quantity= new ArrayList<>();
			for (Product p : list) {
				total += p.getPrice()*p.getQuantity();
			}

			result.setTotalAmount(total);
			cartRepo.save(result);

		} catch (InvalidCartException e) {
			throw new CartServiceException(e.getMessage(), e);
		}
		return result;
	}

	//adding a new cart
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Cart addCart(Cart s, int id) throws CartServiceException {
		System.out.println("Added");
		try {
			User per = userRepo.findById(id).orElse(null);
			Cart pass = null;
			if (per == null) {
				System.out.println("NULL");
				throw new InvalidCartException("User with id:" + id + "not found");
			} else {
				s.setUser(per);
				pass = cartRepo.save(s);
				per.setCart(pass);
				per = userRepo.save(per);
			}
			System.out.println("Added Details");
			return pass;
		} catch (RuntimeException e) {
			throw new CartServiceException(e.getMessage(), e);
		}
	}

}

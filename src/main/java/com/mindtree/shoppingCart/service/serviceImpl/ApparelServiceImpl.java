package com.mindtree.shoppingCart.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingCart.exception.daoException.InvalidCartException;
import com.mindtree.shoppingCart.exception.serviceException.CartServiceException;
import com.mindtree.shoppingCart.model.Apparel;
import com.mindtree.shoppingCart.model.Cart;
import com.mindtree.shoppingCart.model.Product;
import com.mindtree.shoppingCart.repository.ApparelRepo;
import com.mindtree.shoppingCart.repository.CartRepo;
import com.mindtree.shoppingCart.service.ApparelService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ApparelServiceImpl implements ApparelService {

	@Autowired
	private ApparelRepo apparelRepo;

	@Autowired
	private CartRepo cartRepo;

	//getting all products from Apparel DB
	@Override
	public List<Apparel> getAllProducts() throws CartServiceException {
		List<Apparel> li;
		try {
			li = new ArrayList<>();
			apparelRepo.findAll().forEach(li::add);
		} catch (RuntimeException e) {
			throw new CartServiceException(e.getMessage(), e);
		}
		return li;
	}

	//adding apparel product to Apparel DB
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Apparel addApparel(Apparel product, int id) throws CartServiceException {
		Apparel pass;
		try {
			Cart per = cartRepo.findById(id)
					.orElseThrow(() -> new InvalidCartException("Cart with id:" + id + " not found"));
			pass = null;

			int quan = 0;

			pass = apparelRepo.findByName(product.getProductName()).orElse(null);

			Set<Product> li = per.getProducts();

			if (pass == null) {
				product.setQuantity(1);
				pass = apparelRepo.save(product);
			} else {
				quan = pass.getQuantity();
				pass.setQuantity(quan + 1);
				pass = apparelRepo.save(pass);
			}
			per.setProducts(li);
			pass.setCart(per);
			per = cartRepo.save(per);

		} catch (InvalidCartException e) {
			throw new CartServiceException(e.getMessage(), e);
		}
		System.out.println("Added Details");
		return pass;
	}

}

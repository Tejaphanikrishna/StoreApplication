package com.mindtree.shoppingCart.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingCart.model.Cart;


@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	
}

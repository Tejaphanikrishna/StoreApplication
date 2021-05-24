package com.mindtree.shoppingCart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingCart.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

	@Query("Select a from Product a where a.productName=?1")
	Optional<Product> findByName(String productName);
	
}

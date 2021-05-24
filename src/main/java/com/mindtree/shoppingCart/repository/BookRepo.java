package com.mindtree.shoppingCart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingCart.model.Book;


@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{
	
	Optional<Book> findByName(@Param("name") String name);
	
}
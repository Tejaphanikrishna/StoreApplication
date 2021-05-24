package com.mindtree.shoppingCart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingCart.model.Apparel;

@Repository
public interface ApparelRepo extends JpaRepository<Apparel, Integer>{

	Optional<Apparel> findByName(@Param("name") String name);

}

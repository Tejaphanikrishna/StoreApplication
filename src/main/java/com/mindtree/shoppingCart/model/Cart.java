package com.mindtree.shoppingCart.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;

	@OneToOne
	@JsonIgnore
	private User user;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cart")
	private Set<Product> products;

	private float totalAmount;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int cartId, User user, Set<Product> products, float totalAmount) {
		super();
		this.cartId = cartId;
		this.user = user;
		this.products = products;
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", prodcuts=" + getProducts() + "]";
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

}

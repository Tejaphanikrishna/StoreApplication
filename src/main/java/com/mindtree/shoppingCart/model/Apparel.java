package com.mindtree.shoppingCart.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Apparel.findByName", query = "SELECT b FROM Apparel b WHERE b.productName =:name")
public class Apparel extends Product {

	private String type;
	private String brand;
	private String design;

	public Apparel(int productId, String productName, float price, int quantity, Cart cart, String publications,
			String author, String genre) {
		super(productId, productName, price, quantity, cart);
		this.type = type;
		this.brand = brand;
		this.design = design;
	}

	public Apparel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Apparel(int productId, String productName, float price, int quantity, Cart cart) {
		super(productId, productName, price, quantity, cart);
		// TODO Auto-generated constructor stub
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Apparel [type=" + type + ", brand=" + brand + ", design=" + design + "]";
	}
}

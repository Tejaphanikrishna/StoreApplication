package com.mindtree.shoppingCart.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Book.findByName", query = "SELECT b FROM Book b WHERE b.productName =:name")
public class Book extends Product {

	private String genre;
	private String author;
	private String publications;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Book(int productId, String productName, float price, int quantity, Cart cart,String publications,String author,String genre) {
		super(productId, productName, price, quantity, cart);
		this.genre = genre;
		this.author = author;
		this.publications = publications;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublications() {
		return publications;
	}
	public void setPublications(String publications) {
		this.publications = publications;
	}
	@Override
	public String toString() {
		return "Book [genre=" + genre + ", author=" + author + ", publications=" + publications + "]";
	}

}

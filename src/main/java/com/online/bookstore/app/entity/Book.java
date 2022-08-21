package com.online.bookstore.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookId;
	private String bookName; 
	private String bookDescription;
	private String bookAuthor;
	private String bookClassification;
	private double bookPrice;
	private String bookISBN;
	private int bookQuantity;
	private double promotionalDiscountPercentage;
	
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookDescription() {
		return bookDescription;
	}
	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookClassification() {
		return bookClassification;
	}
	public void setBookClassification(String bookClassification) {
		this.bookClassification = bookClassification;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	public int getBookQuantity() {
		return bookQuantity;
	}
	public void setBookQuantity(int bookQuantity) {
		this.bookQuantity = bookQuantity;
	}
	public double getPromotionalDiscountPercentage() {
		return promotionalDiscountPercentage;
	}
	public void setPromotionalDiscountPercentage(double promotionalDiscountPercentage) {
		this.promotionalDiscountPercentage = promotionalDiscountPercentage;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", bookDescription=" + bookDescription
				+ ", bookAuthor=" + bookAuthor + ", bookClassification=" + bookClassification + ", bookPrice="
				+ bookPrice + ", bookISBN=" + bookISBN + ", bookQuantity=" + bookQuantity
				+ ", promotionalDiscountPercentage=" + promotionalDiscountPercentage + "]";
	}
}

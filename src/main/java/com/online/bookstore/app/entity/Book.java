package com.online.bookstore.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookId;

	@NotBlank(message = "Valid Book Name must be provided")
	private String bookName;

	private String bookDescription;

	private String bookAuthor;

	@NotBlank(message = "Valid Book type/classification must be provided")
	private String bookClassification;

	@NotNull(message = "Valid Book price must be provided")
	@Min(value = 1, message = "Book Price should not be less than 1.00")
	private double bookPrice;

	@NotBlank(message = "Valid Book ISBN must be provided")
	private String bookISBN;

	@NotNull(message = "Valid Book quantity must be provided")
	@Min(value = 1, message = "Book Quantity should not be less than 1")
	private int bookQuantity;

	private double promotionalDiscountPercentage;
	
	public Book() { }

	public Book(Long bookId, @NotBlank(message = "Valid Book Name must be provided") String bookName,
			String bookDescription, String bookAuthor,
			@NotBlank(message = "Valid Book type/classification must be provided") String bookClassification,
			@NotNull(message = "Valid Book price must be provided") @Min(value = 1, message = "Book Price should not be less than 1.00") double bookPrice,
			@NotBlank(message = "Valid Book ISBN must be provided") String bookISBN,
			@NotNull(message = "Valid Book quantity must be provided") @Min(value = 1, message = "Book Quantity should not be less than 1") int bookQuantity,
			double promotionalDiscountPercentage) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookDescription = bookDescription;
		this.bookAuthor = bookAuthor;
		this.bookClassification = bookClassification;
		this.bookPrice = bookPrice;
		this.bookISBN = bookISBN;
		this.bookQuantity = bookQuantity;
		this.promotionalDiscountPercentage = promotionalDiscountPercentage;
	}
	
	public Book(@NotBlank(message = "Valid Book Name must be provided") String bookName,
			String bookDescription, String bookAuthor,
			@NotBlank(message = "Valid Book type/classification must be provided") String bookClassification,
			@NotNull(message = "Valid Book price must be provided") @Min(value = 1, message = "Book Price should not be less than 1.00") double bookPrice,
			@NotBlank(message = "Valid Book ISBN must be provided") String bookISBN,
			@NotNull(message = "Valid Book quantity must be provided") @Min(value = 1, message = "Book Quantity should not be less than 1") int bookQuantity,
			double promotionalDiscountPercentage) {
		this.bookName = bookName;
		this.bookDescription = bookDescription;
		this.bookAuthor = bookAuthor;
		this.bookClassification = bookClassification;
		this.bookPrice = bookPrice;
		this.bookISBN = bookISBN;
		this.bookQuantity = bookQuantity;
		this.promotionalDiscountPercentage = promotionalDiscountPercentage;
	}

	public Book(@NotBlank(message = "Valid Book Name must be provided") String bookName,
			@NotBlank(message = "Valid Book type/classification must be provided") String bookClassification,
			@NotNull(message = "Valid Book price must be provided") @Min(value = 1, message = "Book Price should not be less than 1.00") double bookPrice,
			@NotBlank(message = "Valid Book ISBN must be provided") String bookISBN,
			@NotNull(message = "Valid Book quantity must be provided") @Min(value = 1, message = "Book Quantity should not be less than 1") int bookQuantity) {
		this.bookName = bookName;
		this.bookClassification = bookClassification;
		this.bookPrice = bookPrice;
		this.bookISBN = bookISBN;
		this.bookQuantity = bookQuantity;
	}

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

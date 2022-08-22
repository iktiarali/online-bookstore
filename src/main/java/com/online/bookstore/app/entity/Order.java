package com.online.bookstore.app.entity;

import java.util.List;

// Note - Order details also can be stored in DB. For now only returning while buying
public class Order {
	private String orderId;
	private int orderQuantity;
	private List<Book> books;
	private double totalBillAmount;
	private double totalPayableAmount;
	private double totalDiscountAmount;
	
	public Order() { }
	
	public Order(String orderId, int orderQuantity, List<Book> books, double totalBillAmount, double totalPayableAmount,
			double totalDiscountAmount) {
		this.orderId = orderId;
		this.orderQuantity = orderQuantity;
		this.books = books;
		this.totalBillAmount = totalBillAmount;
		this.totalPayableAmount = totalPayableAmount;
		this.totalDiscountAmount = totalDiscountAmount;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public double getTotalPayableAmount() {
		return totalPayableAmount;
	}
	public void setTotalPayableAmount(double totalPayableAmount) {
		this.totalPayableAmount = totalPayableAmount;
	}
	public double getTotalDiscountAmount() {
		return totalDiscountAmount;
	}
	public void setTotalDiscountAmount(double totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}
	public double getTotalBillAmount() {
		return totalBillAmount;
	}
	public void setTotalBillAmount(double totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderQuantity=" + orderQuantity + ", books=" + books
				+ ", totalBillAmount=" + totalBillAmount + ", totalPayableAmount=" + totalPayableAmount
				+ ", totalDiscountAmount=" + totalDiscountAmount + "]";
	}
}

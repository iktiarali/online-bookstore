package com.online.bookstore.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.online.bookstore.app.entity.Book;
import com.online.bookstore.app.entity.Order;

@Service
public interface BookStoreAppService {

	public String welcome();

	public Book addBook(Book book) throws Exception; // Create
	
	public List<Book> getAllBooks(); // Read All
	public Book getBookById(Long bookId) throws Exception; // Read One by Id

	public Book updateBook(Book book) throws Exception; // Update

	public Book deleteBookById(Long bookId) throws Exception; // Delete

	public Order buyBooks(List<Book> books) throws Exception; // Buy
}

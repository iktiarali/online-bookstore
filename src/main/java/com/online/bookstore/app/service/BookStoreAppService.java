package com.online.bookstore.app.service;

import java.util.List;

import com.online.bookstore.app.entity.Book;
import com.online.bookstore.app.entity.Order;
import org.springframework.stereotype.Service;

@Service
public interface BookStoreAppService {

	public String welcome();

	public Book addBook(Book book); // Create
	
	public List<Book> getAllBooks(); // Read All
	public Book getBookById(Long bookId); // Read One by Id

	public Book updateBook(Book book); // Update

	public Book deleteBookById(Long bookId); // Delete

	public Order buyBooks(List<Book> books); // Buy
}

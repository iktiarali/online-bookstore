package com.online.bookstore.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.bookstore.app.entity.Book;
import com.online.bookstore.app.entity.Order;
import com.online.bookstore.app.service.BookStoreAppService;

@RestController
@RequestMapping("/bookstore")
public class BookStoreAppController {
	
	@Autowired
	BookStoreAppService bookStoreAppService;
	
	public BookStoreAppController() { }

	public BookStoreAppController(BookStoreAppService bookStoreAppService) {
		 this.bookStoreAppService = bookStoreAppService;
	}

	@GetMapping("/welcome")
	public ResponseEntity<String> welcome() {
		String welcomeMsg = bookStoreAppService.welcome();
		return new ResponseEntity<String>(welcomeMsg, HttpStatus.OK);
	}

	@GetMapping("/getallbooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> allBooks = bookStoreAppService.getAllBooks();
		return new ResponseEntity<List<Book>>(allBooks, HttpStatus.OK);
	}
	
	@GetMapping("/getbook/{bookid}")
	public ResponseEntity<Book> getBookById(@PathVariable("bookid") Long bookId) throws Exception {
		Book book = bookStoreAppService.getBookById(bookId);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	@PostMapping("/addbook")
	public ResponseEntity<Book> addBook(@RequestBody Book book) throws Exception {
		Book bookAdded = bookStoreAppService.addBook(book);
		return new ResponseEntity<Book>(bookAdded, HttpStatus.CREATED);
	}

	@PutMapping("/updatebook")
	public ResponseEntity<Book> updateBook(@RequestBody Book book) throws Exception {
		Book bookUpdated = bookStoreAppService.updateBook(book);
		return new ResponseEntity<Book>(bookUpdated, HttpStatus.CREATED);
	}

	@DeleteMapping("/deletebook/{bookid}")
	public ResponseEntity<Book> deleteBookById(@PathVariable("bookid") Long bookId) throws Exception {
		Book bookDeleted = bookStoreAppService.deleteBookById(bookId);
		return new ResponseEntity<Book>(bookDeleted, HttpStatus.ACCEPTED);
	}

	//Buying books which will return Order entity with order details and total payable amount
	@PostMapping("/buybooks")
	public ResponseEntity<Order> buyBooks(@RequestBody List<Book> books) throws Exception {
		Order order = bookStoreAppService.buyBooks(books);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
}

package com.online.bookstore.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.online.bookstore.app.controller.BookStoreAppController;
import com.online.bookstore.app.entity.Book;
import com.online.bookstore.app.entity.Order;
import com.online.bookstore.app.repository.BookStoreRepository;
import com.online.bookstore.app.service.BookStoreAppService;
import com.online.bookstore.app.service.BookStoreAppServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BookstoreAppTests {
	
	@Mock
	private BookStoreRepository bookStoreRepo;
	
	@Mock
	private BookStoreAppService bookStoreAppService;
	
	@Autowired
	private BookStoreAppController bookStoreAppController;
	
	@BeforeEach
	void setUp() throws Exception {
		this.bookStoreAppService = new BookStoreAppServiceImpl(this.bookStoreRepo);
		this.bookStoreAppController = new BookStoreAppController(this.bookStoreAppService);
	}
	
	@Before(value = "getBookById")
	void addBookSetUp() throws Exception {
		Book testBook = new Book((long) 1, "test book", "test book desc", "test book author", "fiction", 250.00, "test-book-isbn", 20, 10.00);
		Mockito.when(bookStoreAppService.getBookById(testBook.getBookId()))
	      .thenReturn(testBook);
	}
	
	@Test
	void addBook() throws Exception {
		Book testBook = new Book("test book", "test book desc", "test book author", "fiction", 250.00, "test-book-isbn", 20, 10.00);
		ResponseEntity<Book> responseEntity = bookStoreAppController.addBook(testBook);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	void getAllBooks() {
		bookStoreAppService.getAllBooks();
		verify(bookStoreRepo).findAll();
		
		ResponseEntity<List<Book>> responseEntity = bookStoreAppController.getAllBooks();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	void getBookById() throws Exception {
		Long testBookId = (long) 1;
		ResponseEntity<Book> responseEntity = bookStoreAppController.getBookById(testBookId);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	void updateBook() throws Exception {
		Book testBook = new Book("test book", "test book desc", "test book author", "fiction", 250.00, "test-book-isbn", 20, 10.00);
		ResponseEntity<Book> responseEntity = bookStoreAppController.updateBook(testBook);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}
	
	@Test
	void deleteBookById() throws Exception {
		Long testBookId = (long) 1;
		ResponseEntity<Book> responseEntity = bookStoreAppController.deleteBookById(testBookId);
		assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
	}
	
	@Test
	void buyBooks() throws Exception {
		Book testBook = new Book((long) 1, "test book", "test book desc", "test book author", "fiction", 250.00, "test-book-isbn", 20, 10.00);
		List<Book> testBooks = new ArrayList<Book>();
		testBooks.add(testBook);
		ResponseEntity<Order> responseEntity = bookStoreAppController.buyBooks(testBooks);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
}

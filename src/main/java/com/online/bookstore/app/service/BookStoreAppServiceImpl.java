package com.online.bookstore.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.online.bookstore.app.entity.Book;
import com.online.bookstore.app.entity.Order;
import com.online.bookstore.app.repository.BookStoreRepository;
import com.online.bookstore.app.utility.Utilities;

@Service
public class BookStoreAppServiceImpl implements BookStoreAppService {
	
	@Autowired
	BookStoreRepository bookStoreRepo;
	
	@Override
	public String welcome() {
		String msg = "Welcome to BookStore!";
		System.out.println("Welcome to BookStore!");
		return msg;
	}

	@Override
	public List<Book> getAllBooks() {
		System.out.println("Fetching all books from repository...");
		List<Book> allBooks = fetchAllBooks();
		return allBooks;
	}
	
	@Override
	public Book getBookById(Long bookId) {
		System.out.println("Fetching a book from repository by it's Id...");
		Book book = findBookById(bookId);
		return book;
	}
	
	@Override
	public Book addBook(Book book) {
		System.out.println("Adding a book to repository...");
		Book bookAdded = addBookToDatabase(book);
		return bookAdded;
	}

	@Override
	public Book updateBook(Book book) {
		System.out.println("Update an existing book in repository...");
		Book bookUpdated =  addBookToDatabase(book);
		return bookUpdated;
	}

	@Override
	public Book deleteBookById(Long bookId) {
		System.out.println("Deleting an existing book from repository by it's Id...");
		Book bookToBeDeleted = findBookById(bookId);
		bookStoreRepo.deleteById(bookId);
		return bookToBeDeleted;
	}

	@Override
	public Order buyBooks(List<Book> books) {
		System.out.println("Buying books from repository...");
		Order order = buyBooksFromStore(books);
		return order;
	}

	// Private methods for all operations
	private List<Book> fetchAllBooks() {
		return bookStoreRepo.findAll();
	}
	
	private Book findBookById(Long bookId) {
		Optional<Book> book = bookStoreRepo.findById(bookId);
		if(!book.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Book with Id %d NOT Found in Book Store", bookId));
		else
			return book.get();
	}

	private Book addBookToDatabase(Book book) {
		Book bookAdded = bookStoreRepo.save(book);
		return bookAdded;
	}

	private Order buyBooksFromStore(List<Book> books) {
		double totalBillAmount = 0;
		double totalPayableAmount = 0;
		double totalDiscountAmount = 0;
		int orderQuantity = 0;
		Order order = new Order();
		List<Book> orderedBooks = new ArrayList<Book>();
		
		for (Book book : books) {
			//check if the requested book is valid and existing in db
			Book bookDB = findBookById(book.getBookId()); // Get the book from DB
			orderedBooks.add(bookDB);
			if(bookDB == null) {
				System.out.println("requested book to buy is not available...");
			}else if(bookDB.getBookQuantity() < book.getBookQuantity()) {
				System.out.println("Requested quantity for the book to buy is not available...out of stock...");
			}else {
				int updatedQuantity = bookDB.getBookQuantity() - book.getBookQuantity();
				bookDB.setBookQuantity(updatedQuantity);
				
				// Update database for the book after buying
				bookStoreRepo.save(bookDB); // Update book with new quantity after buying
				
				/*if(updatedQuantity > 0)
					bookStoreRepo.save(bookDB); // Update book with new quantity after buying
				else
					bookStoreRepo.deleteById(bookDB.getBookId()); // Delete book if quantity is 0 after buying*/
				
				orderQuantity += book.getBookQuantity();
				
				// Calculate Bill with promotional discounts
				totalBillAmount += bookDB.getBookPrice() * book.getBookQuantity();
				double eachItemDiscountAmount = (bookDB.getBookPrice() * bookDB.getPromotionalDiscountPercentage()/100);
				double totalItemDiscountAmount = (book.getBookQuantity() * eachItemDiscountAmount);
				totalDiscountAmount += totalItemDiscountAmount;
			}
		}	
		totalPayableAmount = totalBillAmount - totalDiscountAmount;
		order.setBooks(books);
		order.setOrderId(Utilities.generateRandomOrderID());
		order.setOrderQuantity(orderQuantity);
		order.setTotalBillAmount(totalBillAmount);
		order.setTotalPayableAmount(totalPayableAmount);
		order.setTotalDiscountAmount(totalDiscountAmount);
		return order;
	}


}

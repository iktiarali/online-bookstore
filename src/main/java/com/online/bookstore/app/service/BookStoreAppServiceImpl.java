package com.online.bookstore.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

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
	private BookStoreRepository bookStoreRepo;
	
	@Autowired
	private Validator validator;
	
	public BookStoreAppServiceImpl() { }
	
	public BookStoreAppServiceImpl(BookStoreRepository bookStoreRepo) {
        this.bookStoreRepo = bookStoreRepo;
    }
	
	@Override
	public String welcome() {
		String msg = "Welcome to BookStore!";
		System.out.println("Welcome to BookStore!");
		return msg;
	}

	@Override
	public List<Book> getAllBooks() {
		System.out.println("Fetching all books from repository...");
		List<Book> allBooks = bookStoreRepo.findAll();
		return allBooks;
	}
	
	@Override
	public Book getBookById(Long bookId) throws Exception {
		System.out.println("Fetching a book from repository by it's Id...");
		Book book = findBookById(bookId);
		return book;
	}
	
	@Override
	public Book addBook(Book book) throws Exception {
		System.out.println("Adding a book to repository...");
		//Validating request entity book
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		if (!violations.isEmpty()) {
			StringBuilder errorMsgs = new StringBuilder();
            for (ConstraintViolation<Book> constraintViolation : violations) {
            	errorMsgs.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + errorMsgs.toString(), violations);
		}
		Book bookAdded = bookStoreRepo.save(book);
		System.out.println("bookAdded service - " + bookAdded);
		return bookAdded;
	}

	@Override
	public Book updateBook(Book book) throws Exception {
		System.out.println("Update an existing book in repository...");
		//Validating request entity book
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		if (!violations.isEmpty()) {
			StringBuilder errorMsgs = new StringBuilder();
            for (ConstraintViolation<Book> constraintViolation : violations) {
            	errorMsgs.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + errorMsgs.toString(), violations);
		}
		Book bookUpdated =  bookStoreRepo.save(book);
		return bookUpdated;
	}

	@Override
	public Book deleteBookById(Long bookId) throws Exception {
		System.out.println("Deleting an existing book from repository by it's Id...");
		Book bookToBeDeleted = findBookById(bookId);
		bookStoreRepo.deleteById(bookId);
		return bookToBeDeleted;
	}

	@Override
	public Order buyBooks(List<Book> books) throws Exception {
		System.out.println("Buying books from repository...");
		Order order = buyBooksFromStore(books);
		return order;
	}

	// Private methods for all operations
	private Book findBookById(Long bookId) throws Exception {
		Optional<Book> book = bookStoreRepo.findById(bookId);
		if(!book.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Book with Id %d NOT Found in Book Store", bookId));
		else
			return book.get();
	}

	private Order buyBooksFromStore(List<Book> books) throws Exception {
		double totalBillAmount = 0;
		double totalPayableAmount = 0;
		double totalDiscountAmount = 0;
		int orderQuantity = 0;
		Order order = new Order();
		List<Book> orderedBooks = new ArrayList<Book>();
		
		for (Book book : books) {
			//Validating request entity book
			Set<ConstraintViolation<Book>> violations = validator.validate(book);
			if (!violations.isEmpty()) {
				StringBuilder errorMsgs = new StringBuilder();
	            for (ConstraintViolation<Book> constraintViolation : violations) {
	            	errorMsgs.append(constraintViolation.getMessage());
	            }
	            throw new ConstraintViolationException("Error occurred: " + errorMsgs.toString(), violations);
			}
			
			Book bookDB = findBookById(book.getBookId()); // Checking and getting the book from DB
			orderedBooks.add(bookDB);
			if(book.getBookQuantity() <= bookDB.getBookQuantity()) { // Checking the stock for the book
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
			}else
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Book with Id %d NOT having required quantity %d in Book Store", book.getBookId(), book.getBookQuantity()));
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

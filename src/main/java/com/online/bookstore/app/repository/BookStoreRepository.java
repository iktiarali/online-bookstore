package com.online.bookstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.bookstore.app.entity.Book;

public interface BookStoreRepository extends JpaRepository<Book, Long>{
	
}
